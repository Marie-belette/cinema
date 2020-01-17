package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TestDateTime {

	@Test
	void testParseFrenchDate() {
		String dateStr = "15/01/2020";
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(date);
		assertAll(
				() -> assertEquals(15, date.getDayOfMonth(), "day"),
				() -> assertEquals(02, date.getMonthValue(), "month"),
				() -> assertEquals(2022, date.getYear(), "year")); // pr�sent� comme �a, permet d'indiquer toutes les erreurs.
	}

	@Test
	void testFormatDate() {
		LocalDate date = LocalDate.now();
		var formats = List.of(
			DateTimeFormatter.ofPattern("dd/MM/yyyy"),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy"),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", Locale.US),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("es", "es")),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("lv", "lv")),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("ru", "ru")),
			DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("ja", "ja")));
		formats.forEach(f -> System.out.println(date.format(f)));
	}
		
	@Test
	void testLocaleInde() {
		var date = LocalDate.now();
		Arrays.stream(Locale.getAvailableLocales())
					.filter(l -> l.getCountry().contentEquals("IN"))
					//.forEach(System.out::println)
					.map(l -> date.format(DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", l)))
					.forEach(System.out::println);
		}
	
	@Test
	void testHeureAutourDuMonde() {
		var fmtHeure = DateTimeFormatter.ofPattern("kk:mm:ss");
		var fmtDate =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		var dtHere = LocalDateTime.now();
		System.out.println("Ici nous sommes le " + dtHere.format(fmtDate) + " et il est " + dtHere.format(fmtHeure));
		var dtNY = LocalDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("A New York nous sommes le " + dtNY.format(fmtDate) + " et il est " + dtNY.format(fmtHeure));
		// System.out.println(ZoneId.SHORT_IDS);
		var dtNZ = LocalDateTime.now(ZoneId.of("Pacific/Auckland"));
		System.out.println("A Auckland nous sommes le " + dtNZ.format(fmtDate) + " et il est " + dtNZ.format(fmtHeure));
		var dtMidway = LocalDateTime.now(ZoneId.of("Pacific/Midway"));
		System.out.println("A Midway nous sommes le " + dtMidway.format(fmtDate) + " et il est " + dtMidway.format(fmtHeure));
	}
}
