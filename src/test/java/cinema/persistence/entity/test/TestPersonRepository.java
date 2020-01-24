package cinema.persistence.entity.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Person;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestPersonRepository {

		@Autowired
		PersonRepository repoPerson;
		
		@Autowired
		EntityManager entityManager;

		@Test
		void testSave() {
			//given
			Person person = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));
			//when
			repoPerson.save(person);
			//then
			System.out.println(person);
		}
	
}

