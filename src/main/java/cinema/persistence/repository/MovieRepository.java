package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Set<Movie> findByTitle(String title);
	Set<Movie> findByTitleContainingIgnoreCase(String title);
	Set<Movie> findByYear(Integer year);
	Set<Movie> findByYearGreaterThan(Integer year);
	Set<Movie> findByYearBetween(int year, int year2);
	Set<Movie> findByTitleAndYear(String title, int year);
	Set<Movie> findByDirector(Person director);
	Set<Movie> findByDirectorNameEndingWith(String name);
	Set<Movie> findByDirectorNameContainingIgnoreCase(String name);
	Set<Movie> findByActors(Person actor);
	Set<Movie> findByActorsIdPerson (int idActor);
	Set<Movie> findByActorsNameContainingIgnoreCase(String name);
	Set<Movie> findByDirectorName(String directorName);
	Set<Movie> findByActorsName(String actorName);
}

