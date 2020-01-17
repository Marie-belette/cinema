package test.tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import tools.Trifunction;

class TestTriFunction {

	@Test
	void test() {
		Trifunction<String, Integer, Integer, Movie> f;
		f = Movie::new;
		var m = f.apply("Joker", 2019, 165);
		System.out.println(m);
		System.out.println(m.getClass());
	}

}
