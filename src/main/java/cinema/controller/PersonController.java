package cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

public class PersonController {
	
	@Autowired
	IPersonService personService;
	
	@GetMapping
	List<Person>  getAllPersons() {
		return personService.getAllPersons();
	}

	@PostMapping
	Person addPerson(Person person) {
	// TODO
	return person;
	}


}
