package cinema.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import cinema.persistence.entity.Person;

public class PersonController {
	
	@GetMapping
	List<Person>  getAllPersons() {
		//TODO
		return null;
	}

	@PostMapping
	Person addPerson(Person person) {
	// TODO
	return person;
}


}
