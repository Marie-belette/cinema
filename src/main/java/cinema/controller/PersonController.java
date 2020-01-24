package cinema.controller;

import org.springframework.web.bind.annotation.PostMapping;

import cinema.persistence.entity.Person;


public class PersonController {

	@PostMapping
	Person addPerson(Person person) {
	// TODO
	return person;
}


}
