package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Movie;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getMovieByPartialTitle(String partialTitle) {
		return movieRepository.findByTitleContainingIgnoreCase(partialTitle); 
	}

	@Override
	public Set<Movie> getMovieByActorId(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor);
	}

	@Override
	public Set<Movie> getMovieByActorName(String actorName) {
		return movieRepository.findByActorsName(actorName);
	}

	@Override
	public Set<Movie> getMovieByDirectorId(int idDirector) {
		var directorOpt = personRepository.findById(idDirector);
		return directorOpt.map(d -> movieRepository.findByDirector(d))
				.orElseGet(() -> Collections.emptySet());
	}

	@Override
	public Set<Movie> getMovieByTitleAndYear(String title, int year) {
		return movieRepository.findByTitleAndYear(title, year);
	}

	@Override
	public Set<Movie> getMovieByYearBetween(int year1, int year2) {
		return movieRepository.findByYearBetween(year1, year2);
	}

	@Override
	public Set<Movie> getMovieByDirectorName(String directorName) {
		return movieRepository.findByDirectorName(directorName);
	}

}
