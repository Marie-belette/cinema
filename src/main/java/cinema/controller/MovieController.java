package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
	
	@Autowired
	IMovieService movieService;
		
	@GetMapping
	@ResponseBody
	public List<Movie> allMovies() {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id")int idMovie) {
		return movieService.getMovieById(idMovie);
	}
	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByPartialTitle(@RequestParam("t") String partialTitle) {
		return movieService.getMovieByPartialTitle(partialTitle); 
	}
	
	@GetMapping("/byTitleAndYear")
	@ResponseBody
	public Set<Movie> movieByTitleAndYear(@RequestParam("t") String title, @RequestParam("y") int year) {
		return movieService.getMovieByTitleAndYear(title, year);
	}
	
	@GetMapping("/byYearBetween")
	@ResponseBody
	public Set<Movie> movieByYearBetween(@RequestParam("y1") int year1, @RequestParam("y2") int year2) {
		return movieService.getMovieByYearBetween(year1, year2);
	}
	
	@GetMapping("/byDirectorName")
	@ResponseBody
	public Set<Movie> movieByDirectorName(@RequestParam("d") String directorName) {
		return movieService.getMovieByDirectorName(directorName);
	}
	
	@GetMapping("/byDirectorId")
	@ResponseBody
	public Set<Movie> movieByDirectorId(@RequestParam("d") int idDirector) {
		return movieService.getMovieByDirectorId(idDirector);
	}
	
	@GetMapping("/byActor")
	@ResponseBody
	public Set<Movie> movieByActorName(@RequestParam("a") String actorName) {
		return movieService.getMovieByActorName(actorName);
	}
	
	@GetMapping("/byActorId")
	@ResponseBody
	public Set<Movie> movieByActorId(@RequestParam("a") int idActor) {
		return movieService.getMovieByActorId(idActor);
	}
	
//	@PostMapping
//	@ResponseBody
//	public Movie addMovie(@RequestBody Movie movie) {
//		Movie movieSaved = movieRepository.save(movie);
//		movieRepository.flush();
//		return movieSaved;
//	}
//	
//	@PutMapping("/addActor")
//	@ResponseBody
//	public Optional<Movie> addActor(@RequestParam("a") int idActor, @RequestParam("m") int idMovie) {
//		//TODO : anywhere else
//		var movieOpt = movieRepository.findById(idMovie);
//		var actorOpt = personRepository.findById(idActor);
//		if (movieOpt.isPresent() && actorOpt.isPresent()) {
//			movieOpt.get().getActors().add(actorOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//		//
//	}
//	
//	@PutMapping("/setDirector")
//	@ResponseBody
//	public  Optional<Movie> setDirector(@RequestParam("m") int idMovie, @RequestParam("d") int idDirector) {
//		var optMovie = movieRepository.findById(idMovie);
//		var optDirector = personRepository.findById(idDirector);
//		//TODO : anywhere else
//		if (optMovie.isPresent() && optDirector.isPresent()) {
//			optMovie.get().setDirector(optDirector.get());
//		};
//		movieRepository.flush();
//		//
//		return optMovie;
//	}
//	
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Movie> modifyMovie(@RequestBody Movie movie) {
//		var optMovie = movieRepository.findById(movie.getIdMovie());
//		//TODO : anywhere else
//		optMovie.ifPresent(m -> {
//			m.setTitle(movie.getTitle());
//			m.setYear(movie.getYear());
//			m.setDuration(movie.getDuration());
//			m.setDirector(movie.getDirector());
//		});
//		movieRepository.flush();
//		//
//		return optMovie;
//	}
//	
//	@DeleteMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> deleteMovie(@PathVariable("id") int idMovie) {
//		var movieToDelete = movieRepository.findById(idMovie);
//		movieToDelete.ifPresent(m -> { 
//			movieRepository.delete(m);
//			movieRepository.flush();
//		});
//		return movieToDelete;
//	}
}
