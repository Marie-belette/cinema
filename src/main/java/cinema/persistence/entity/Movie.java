package cinema.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "movies")
public class Movie {

	private Integer idMovie;
	private String title;
	private Integer year;
	private Integer duration;
	
	private Person director;
	private List<Person> actors;
	
	//@Transient
	// private String director; Ce sont les attributs qui ne sont pas "persistent", donc qui ne seront pas sauvegardés (côté Database)
	
	public Movie() {
		this(null, null);
	}

	public Movie(Integer idMovie, String title, Integer year, Integer duration, Person director) {
		super();
		this.idMovie = idMovie;
		this.title = title;
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<>();
	}
	
	public Movie(String title, Integer year, Integer duration, Person director) {
		this(null, title, year, duration, director);
	}
	
	public Movie(String title, Integer year, Integer duration) {
		this(null, title, year, duration, null);
	}

	public Movie(String title, Integer year) {
		this(null, title, year, null, null);
	}
	
	public Movie(String title, Integer year, Person director) {
		this(null, title, year, null, director);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie")
	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}
	
	@Column(nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@ManyToOne
	@JoinColumn(name="id_director", nullable=true)
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	@ManyToMany //(fetch = FetchType.EAGER)
	@JoinTable(name="act",
        joinColumns=@JoinColumn(name="id_movie"),
        inverseJoinColumns=@JoinColumn(name="id_actor")
        )
	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		return builder.append(" (")
		.append(year)
		.append(')')
		.append('#')
		.append(idMovie)
		.toString();
	}
	
}
