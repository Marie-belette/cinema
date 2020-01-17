package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TestMap {

	@Test
	void testMap() {
		Map<Integer,Integer> nbMovieByYear = new TreeMap<>();
		nbMovieByYear.put(2019, 23);
		nbMovieByYear.put(2017, 12);
		nbMovieByYear.put(2014, 7);
		nbMovieByYear.put(1992, 22);
		System.out.println(nbMovieByYear);
		nbMovieByYear.put(2014, 13); // "put" est l'équivalent pour Map de "add" ET de "replace"
		System.out.println(nbMovieByYear);
		System.out.println("En 2017 : " + nbMovieByYear.get(2017));
		for (var year: nbMovieByYear.keySet()) {
			System.out.println(" * year: " + year);
		}
		for (var year: nbMovieByYear.values()) {
			System.out.println(" ° nb movies" + year);
		}

	}
	
}
