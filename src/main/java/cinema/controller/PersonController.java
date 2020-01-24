package cinema.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import cinema.persistence.entity.Person;

public class PersonController {
	
	@GetMapping
	List<Person>  getAllPersons() {
		//TODO
		return null;
	}

}
