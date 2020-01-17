package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestMovie {

	@Test
	void testFirst() {
		Movie movie1 = new Movie ("Joker", 2019, 165); // new = créer un objet (c'est une commande) Movie = la classe. Le nom de la classe + les parenthèses
		// = on fait appel à un constructeur. Le constructeur d'une classe est une pseudo-méthode qui a le nom de la classe.
		Movie movie2 = new Movie ("Helzapoppin", 1947, 84);
		Movie movie3 = new Movie ("Interstellar", 2014);
		List<Movie> movies = List.of(movie1, movie2, movie3);
		System.out.println("Movies:" + movies);
		//
		movie1.setYear(1900);
		System.out.println(movie1);
		System.out.println(movies.get(0));
	}
	
	@Test
	void testEquals1 () {
		Movie movieJ = new Movie ("Joker", 2019, 165);
		Movie movieP = new Movie ("Parasite", 2019, 132);
		Movie movie = movieJ;
		System.out.println(movieJ == movieP);
		System.out.println(movie == movieJ);
	}

	@Test
	void testEquals() {
		Movie movieChaosI = new Movie ("Chaos", 2005);
		Movie movieChaosII = new Movie ("Chaos", 2005);
		System.out.println(movieChaosI == movieChaosII);
		System.out.println(movieChaosI.equals(movieChaosII));	
	}
	
	@Test
	void testCreateData() {
		Movie movie1 = new Movie ("Joker", 2019, 165);
		Movie movie2 = new Movie ("Parasite", 2019, 132);
		Movie movie3 = new Movie ("Interstellar", 2014);
		List<Movie> movies = List.of(movie1, movie2, movie3);
		System.out.println("Movies:" + movies);
		System.out.println(movies.get(0));
		//add directors
		Person tp = new Person("Todd Phillips");
		movie1.setDirector(tp);
		System.out.println(movie1.getTitle() + ", réalisé par " + movie1.getDirector().getName());
		// Clint Eastwood
		Person clint = new Person("Clint Eastwook", LocalDate.of(1930, 05, 31));
		System.out.println(clint + " a " + clint.getAge().getAsInt() + " ans");
		Movie movieGT = new Movie("Gran Torino", 2008, 116, clint);
		Movie movieI = new Movie("Impitoyable", 1992, clint);
		System.out.println("Director of " + movieGT.getTitle() + " : " + movieGT.getDirector().getName());

	}
	
}
