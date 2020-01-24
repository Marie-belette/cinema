//ça s'appelle IMovieService parce que c'est une INTERFACE. La classe d'implémentation est celle qui commence par [arobase]service

package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Movie;

public interface IMovieService {
	List<Movie> getAllMovies();
	Optional<Movie> getMovieById(int idMovie);
	Set<Movie> getMovieByPartialTitle(String partialTitle);
	Set<Movie> getMovieByActorId(int idActor);
	Set<Movie> getMovieByActorName(String actorName);
	Set<Movie> getMovieByDirectorId(int idDirector);
	Set<Movie> getMovieByTitleAndYear(String title, int year);
	Set<Movie> getMovieByYearBetween( int year1, int year2);
	Set<Movie> getMovieByDirectorName(String directorName);
}
