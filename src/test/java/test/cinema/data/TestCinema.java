package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestCinema {
	
	private List<Movie> movies;
	private List<Person> persons;
	
	@BeforeEach
	void initData() {
		persons = new ArrayList<>();
		Collections.addAll(persons,
				new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),		//0
				new Person ("Gérard Darmon", LocalDate.of(1948,  2, 29)),		//1
				new Person ("Todd Phillips", LocalDate.of(1970, 12, 20)),		//2
				new Person("Clint Eastwood", LocalDate.of(1930, 5, 31)),		//3
				new Person("Bradley Cooper", LocalDate.of(1975, 1, 5)),			//4
				new Person ("Bong Joon-Ho", LocalDate.of(1969, 9, 14)),			//5
				new Person ("Christopher Nolan", LocalDate.of(1970, 7, 30)),	//6
				new Person ("Gene Hackman", LocalDate.of(1930, 1, 30)),			//7
				new Person ("Morgan Freeman", LocalDate.of(1937,6,1))			//8
				);
		var clint = persons.get(3);
		var todd = persons.get(2);
		var bong = persons.get(5);
		var nolan = persons.get(6);
		
		movies = new ArrayList<>();
		Collections.addAll(movies, 
				new Movie ("Joker", 2019, 165, todd),						//O
				new Movie ("Parasite", 2019, 132, bong),					//1
				new Movie ("Interstellar", 2014, nolan),					//2
				new Movie ("Gran Torino", 2008, 116, clint),				//3
				new Movie ("Impitoyable", 1992, clint),						//4
				new Movie ("American Sniper", 2014, 133, clint),			//5
				new Movie ("Very Bad Trip", 2009, 100, todd),				//6
				new Movie ("Avengers: Infinity War", 2018, 260),
				new Movie ("Avengers: Endgame", 2019, 182),
				new Movie ("Avengers", 2012, 143),
				new Movie ("Captain Marvel", 2019, 125),
				new Movie ("Avengers: Age of Ultron", 2015, 142)
				);
		movies.get(0).addActor(persons.get(0));
		movies.get(4).addAllActor(persons.get(3), persons.get(5), persons.get(6));
		var actorsParasite = List.of(
				new Person("Kang-ho Song"),
				new Person("Yeo-jeong Jo"),
				new Person ("Woo-sik Choi"),
				new Person ("Jeong-eun Lee"));
		persons.addAll(actorsParasite);
		movies.get(1).addAllActors(actorsParasite);
	}
	
	@Test
	void testParasite() {
		var movie = movies.get(1);
		var actorsText = movie.streamActors()
				.map(Person::getName)
				.collect(Collectors.joining(", "));
		System.out.println("Acteurs de Parasite : " + actorsText);
	}
	
	@Test
	void testParasiteIterable() {
		var movie = movies.get(1);
		for (var it = movie.iteratorActors(); it.hasNext(); ) {
			var actor = it.next();
			System.out.println(actor);
		}
	}

	@Test
	void testModifiableList() {
		System.out.println(persons);
		System.out.println(persons.getClass());
		persons.add(new Person ("Bradley Cooper", LocalDate.of(1975, 1, 5)));
		System.out.println(persons);
	}
	
	@Test
	void DisplayMoviesForEach() {
		System.out.println("Movies list :");
		for (var movie: movies) {
			System.out.println(" - " + movie.getTitle() + ", directed by " + movie.getDirector().getName());
		}
	}

	@Test
	void displayMoviesForI() {
		for (int i=0; i < movies.size(); i++) {
			var movie = movies.get(i);
			System.out.println(" - " + movie.getTitle() + ", directed by " + movie.getDirector().getName());
		}
	}
	
	@Test
	void totalDurationMoviesDirectedByClintEastwood() {
		int totalDuration = 0;
		var clint = persons.get(3);
		for (var movie: movies) {
			if (clint.equals(movie.getDirector())) {
				totalDuration += movie.getDuration();
			}
		}
		System.out.println("Total duration of movies directed by Clint Eastwood: " + totalDuration + " min");
		//en SQL :
		// select sum(duration) from movie
		// where id_director = (select id_person from person where name = 'ClintEastwood')
	}
		
	@Test
	void totalDurationMoviesDirectedByClintEastwoodStreaming() {
		var clint = persons.get(3);
		int totalDuration = movies.stream()
			.filter(m -> m.getDirector().equals(clint))
			//.forEach(System.out::println)
			.mapToInt(Movie::getDuration) // .map tout court nous propose un objet de classe Integer plut�t qu'un int, beaucoup
//			manipulable et qui ne produit pas des pics d'utilisation de m�moire.
//			.forEach(System.out::println)
			.sum();
		System.out.println("Total duration of movies directed by Clint Easwood : " + totalDuration + " minutes");
	}
//	@Test
//	void testSortedMovies() {
//		SortedSet<Movie> sortedMovies = new TreeSet<Movie>(m1,m2 -> -1);
//		sortedMovies.addAll(movies);
//		System.out.println(movies);
//	}
	
	@Test
	void testSortMovies() {
		Collections.sort(movies, 
				Comparator.comparing(Movie::getYear, Comparator.reverseOrder()).thenComparing(Movie::getTitle));	
				// �quivalent de : .thenComparing(m->m.getTitles());
		System.out.println(movies);
	}
	
	@Test
	void testAvengersFirstYear() {
		int firstYear = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.mapToInt(Movie::getYear)
				.min()
				.getAsInt();
			System.out.println("The first Avenger movie hit the screen in : " + firstYear);	
	}
	
	@Test
	void testAvengersFirstAndLastYear() {
		var stats = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.mapToInt(Movie::getYear)
				.summaryStatistics();
			System.out.println("The first Avenger movie hit the screen in : " + stats.getMin());	
			System.out.println("The last Avenger movie to date hit the screen in : " + stats.getMax());
	}
	
	@Test
	void testListeChronologiqueAvengersMovies() {
		var avengersMovies = movies.stream()
			.filter(m -> m.getTitle().contains("Avengers"))
			.collect(Collectors.toCollection(() -> new TreeSet<>(
					Comparator.comparing(Movie::getYear)
					))) ; //supplier, accumulator, combiner, (finisher)
		System.out.println(avengersMovies);
	}
	
	@Test
	void titlesAvengersMovies() {
		var joinedTitles = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.map(Movie::getTitle)
				.collect(Collectors.joining(", "));
			System.out.println(joinedTitles);
	}
	
	@Test
	void testLimit() {
		movies.stream()
			.filter(m -> m.getYear() > 1900)
			.limit(5)
			.forEach(System.out::println);
	}
	
	@Test
	void testNombreFilmsPlusDeDeuxHeures() {
		long nbMoviesOver2h = movies.stream()
				.filter(m -> m.getDuration()>=120)
				.count();
			System.out.println("The number of movies more than 2h long is : " + nbMoviesOver2h);	
	}
	
	@Test
	void testPlusLongTitre() {
		 var MaxLength = movies.stream()
				.map(Movie::getTitle)
				.mapToInt(String::length)
				.max()
				.getAsInt();
		 System.out.println("The longest title has " + MaxLength + " characters");
		 var titlesMaxLength = movies.stream()
		 	.map(Movie::getTitle)
		 	.filter(t -> t.length() == MaxLength)
		 	.collect(Collectors.toList());
		 System.out.println("Film(s) au titre le plus long : " + titlesMaxLength);
	}
	
	@Test
	void nbMovieByYear() {
		var res = movies.stream()
			.collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()));
		System.out.println(res);
	}
	
	@Test
	void nbMovieByDirector() {
		var nbMovieByDir = movies.stream()
			.map(Movie::getDirector)
			.filter(Objects::nonNull)
			.collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
		System.out.println(nbMovieByDir);
	}
	
	@Test
	void nbMovieByDirectorAutreVersion() {
		var nbMovieByDir = movies.stream()
			.filter(m -> Objects.nonNull(m.getDirector()))
			.collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
		System.out.println(nbMovieByDir);
	}
	
	@Test
	void Filmographies() {
		var filmographies = movies.stream()
			.filter(m -> Objects.nonNull(m.getDirector()))
			.collect(Collectors.groupingBy(Movie::getDirector, 
					Collectors.toCollection(
					() -> new TreeSet<>(Comparator.comparing(Movie::getYear, 
							Comparator.reverseOrder())))));
		System.out.println(filmographies);
	}
	
	@Test
	void personsByDecade() {
		var res = persons.stream()
		.collect(Collectors.groupingBy(p -> p.getBirthdate().getYear()/ 10));
		System.out.println(res);
	}
	
}
