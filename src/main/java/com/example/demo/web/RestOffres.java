package com.example.demo.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OffreRepository;
import com.example.demo.entities.Offre;

@RestController
@RequestMapping("/Offres") 
public class RestOffres { 
	
@Autowired
OffreRepository offreRepository; 

@GetMapping
public List<Offre> getAll() { 
return offreRepository.findAll(); 
} 


@GetMapping("/{uid}") 
public Offre getbyid(@PathVariable Long uid ) { 
 
return offreRepository.findById(uid).get(); 
}

@PostMapping
public Offre saveoffre(@RequestBody Offre newoffre) { 
return offreRepository.save(newoffre); 
}

@DeleteMapping("/{id}") 
public void deleteoffre(@PathVariable Long id) { 
offreRepository.deleteById(id); 
} 

@PutMapping("/Offres/{id}")
public Offre updateOffre(@RequestBody Offre newOffre,@PathVariable Long id) {
	return offreRepository.findById(id)
			.map(Offre ->{
				Offre.setIntitulé(newOffre.getIntitulé());
				Offre.setNbpostes(newOffre.getNbpostes());
				Offre.setPays(newOffre.getPays());
				Offre.setSociété(newOffre.getSociété());
				Offre.setSpecialité(newOffre.getSpecialité());
				return offreRepository.save(newOffre);
			})
			.orElseGet(()->{
			newOffre.setcode(id);
			return offreRepository.save(newOffre);
			});

}
}
