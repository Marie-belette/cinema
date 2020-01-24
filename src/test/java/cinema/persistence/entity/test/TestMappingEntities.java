package cinema.persistence.entity.test;
/**
 * this is not a unit test case
 */

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEntities {
	
	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovies;

	@Rollback(false)
	@Test
	void testSaveData() {
		var joaq = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28));
		var gege = new Person ("Gérard Darmon", LocalDate.of(1948,  2, 29));
		var todd = new Person ("Todd Phillips", LocalDate.of(1970, 12, 20));
		var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));
		var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));
		var bong = new Person ("Bong Joon-Ho", LocalDate.of(1969, 9, 14));
		var nolan = new Person ("Christopher Nolan", LocalDate.of(1970, 7, 30));
		var gene = new Person ("Gene Hackman", LocalDate.of(1930, 1, 30));
		var morgan = new Person ("Morgan Freeman", LocalDate.of(1937,6,1));
		var persons = List.of(joaq, gege, todd, clint, brad, bong, nolan, gene, morgan);
		persons.forEach(repoPersons::save);		
		var joker = new Movie ("Joker", 2019, 165, todd);
		var parasite = new Movie ("Parasite", 2019, 132, bong);
		var interstellar = new Movie ("Interstellar", 2014, nolan);
		var granTorino = new Movie ("Gran Torino", 2008, 116, clint);
		var impitoyable = new Movie ("Impitoyable", 1992, clint);
		var americanSniper = new Movie ("American Sniper", 2014, 133, clint);
		var veryBadTrip = new Movie ("Very Bad Trip", 2009, 100, todd);
		var avengersInfinityWar = new Movie ("Avengers: Infinity War", 2018, 260);
		var avengersEndGame = new Movie ("Avengers: Endgame", 2019, 182);
		var avengers = new Movie ("Avengers", 2012, 143);
		var captainMarvel = new Movie ("Captain Marvel", 2019, 125);
		var avengersAgeOfUltron = new Movie ("Avengers: Age of Ultron", 2015, 142);
		var lesPleinsPouvoirs = new Movie ("Les Pleins Pouvoirs", 1997, 121, clint);
		var movies = List.of(
				joker, parasite, interstellar, granTorino, impitoyable, americanSniper, veryBadTrip, avengersInfinityWar, avengersEndGame, avengers, captainMarvel, avengersAgeOfUltron, lesPleinsPouvoirs);
		movies.forEach(repoMovies::save);
	}
	
	@Rollback(false)
	@Test
	void testAddDirector() {
		var movies = repoMovies.findByTitle("Avengers");
		if (movies.size() > 0) {
			var avengers = movies.stream().findFirst().get();
			var joss = new Person("Joss Whedon", LocalDate.of(1964, 06, 23));
			repoPersons.save(joss);
			avengers.setDirector(joss);
		}
	}

	@Rollback(false)
	@Test
	void testSelectMovieWithDirector() {
		var movies = repoMovies.findByTitle("Interstellar");
		if (movies.size() > 0) {
			var interstellar = movies.stream().findFirst().get();		
			var director = interstellar.getDirector();
			System.out.println(director);
			}
	}
	
	@Rollback(false)
	@Test
	void testAddMovie() {
		Movie batmanTheDarkKnight = new Movie ("Batman, The Dark Knight", 2008, 153);
		repoMovies.save(batmanTheDarkKnight);
		repoMovies.flush();
		var nolan = repoPersons.findByName("Christopher Nolan")
				.stream().findFirst().get();
			batmanTheDarkKnight.setDirector(nolan);
			repoMovies.flush();
	}
	
	@Test
	void scenario06SelectPersonByYear() {
		var data = repoPersons.findByBirthdateYear(1930);
		System.out.println(data);
	}
	
	@Rollback(false)
	@Test
	void scenarion07MovieWithActor() {
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		var clint = repoPersons.findByName("Clint Eastwood").stream().findFirst().get();
		var gene = repoPersons.findByName("Gene Hackman").stream().findFirst().get();
		impitoyable.setActors(List.of(clint, gene));
		repoMovies.flush();
	}
	
	@Test
	void testLazyActors() {
		//read a movie : select the movie + its director
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		//read actors
		// var actors = impitoyable.getActors();
		// System.out.println(actors);
	}
	
//	@Rollback(false)
//	@Test
//	void scenarion08MovieAddActor() {
//		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
//		var morgan = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
//		impitoyable.getActors().add(morgan);
//		repoMovies.flush();
//	}
}

