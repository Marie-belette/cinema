package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestMovieRepository {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	void testSave() {
		Movie movie = new Movie("Joker", 2019);
		movieRepository.save(movie);
		var id = movie.getIdMovie();
		System.out.println("Id new movie: " + id);
		assertNotNull(id);
	}
	
	@Test
	void testSaveWithDirector() {
		// Given
		Person person1 = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));
		Movie movie = new Movie("Joker", 2019, 165, person1);
		entityManager.persist(person1); // already in cache
		//when
		movieRepository.save(movie);
		//then
		System.out.println(movie);
		System.out.println(person1);
	}
	
	@Test
	void testSelectAll() {
		// given
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),						
				new Movie ("Parasite", 2019, 132),					
				new Movie ("Interstellar", 2014),					
				new Movie ("Gran Torino", 2008, 116));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findAll();
		// then
		dataRead.forEach(System.out::println);
		assertEquals(data.size(), dataRead.size());
		dataRead.stream()
			.map(Movie::getTitle)
			.allMatch(tr -> data.stream()
					.map(Movie::getTitle)
					.anyMatch(th -> th.equals(tr))
							);
	}
	
	@Test
	void testFindById() {
		// given
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(movie);
		var id = movie.getIdMovie();
		// when
		var movieReadOpt = movieRepository.findById(id);
		System.out.println(movieReadOpt);
		assertAll(
		() -> assertTrue(movieReadOpt.isPresent()),
		() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle()));
	}
	
	@Test
	void testFindByTitle() {
		// given
		String title = "Le Roi Lion";
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),						
				new Movie (title, 2019),					
				new Movie (title, 1994));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findByTitle(title);
		System.out.println(dataRead);
	}
	
	@Test
	void testWhereYearEquals2019() {
		//given
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),						
				new Movie ("Le Roi Lion", 2019),					
				new Movie ("Le Roi Lion", 1994));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findByYear(2019);
		System.out.println(dataRead);
	}
	
	@Test
	void testWhereYearGreaterThan2010() {
		//given
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),						
				new Movie ("Le Roi Lion", 2019),					
				new Movie ("Le Roi Lion", 1994));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findByYearGreaterThan(2010);
		System.out.println(dataRead);
	}
	
	@Test
	void testWhereYearBetween1995And2015() {
		//given
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),
				new Movie ("Le Roi Lion", 2019),
				new Movie ("Seven", 1995),
				new Movie ("Mad Max: Fury Road", 2015),
				new Movie ("Gran Torino", 2008),
				new Movie ("Le Roi Lion", 1994));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findByYearBetween(1995, 2015);
		// then
		System.out.println(dataRead);
		assertAll(
		() -> assertEquals(3, dataRead.size()),
		() -> assertTrue(dataRead.stream()
			.mapToInt(Movie::getYear)
			.allMatch(y -> (y>=1995) && (y<=2015))));
	}
	
	@Test
	void testWhereTitleIsLeRoiLionAndYearIs1994() {
		//given
		String title = "Le Roi Lion";
		List<Movie> data = List.of(
				new Movie ("Joker", 2019, 165),		
				new Movie ("Mad Max: Fury Road", 2015),
				new Movie ("Gran Torino", 2008),
				new Movie (title, 2019),
				new Movie ("Forrest Gump", 1994),
				new Movie (title, 1994));
		data.forEach(entityManager::persist);
		// when
		var dataRead = movieRepository.findByTitleAndYear("Le Roi Lion", 1994);
		// then
		System.out.println(dataRead);
		// TODO : asserts
	}
	
	@Test
	void  findByActorsNameContainingIgnoreCase() {
		//given
		var roiLion = new Movie ("Le Roi Lion", 2019);
		var lArmeFatale = new Movie ("L'Arme Fatale", 1987);
		var madMax = new Movie ("Mad Max", 1979);
		var movies = List.of(roiLion, lArmeFatale, madMax);
		movies.forEach(entityManager::persist);
		var melGibson = new Person ("Mel Gibson");
		var whoopi = new Person ("Whoopi Goldberg");
		var danny = new Person("Danny Glover");
		entityManager.persist(melGibson);
		entityManager.persist(whoopi);
		entityManager.persist(danny);
		roiLion.getActors().add(whoopi);
		madMax.getActors().add(melGibson);
		Collections.addAll(lArmeFatale.getActors(), melGibson, danny);
		entityManager.flush();
		//when
		var  moviesWithMel = movieRepository.findByActorsNameContainingIgnoreCase("Gibson");
		//then
		assertAll(
		() -> assertTrue(moviesWithMel.contains(madMax), "Mad Max"),
		() -> assertTrue(moviesWithMel.contains(lArmeFatale), "L'Arme Fatale"),
		() -> assertFalse(moviesWithMel.contains(roiLion), "Le Roi Lion"));
	}
	
}
