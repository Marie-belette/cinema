package cinema.persistence.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "Persons")
	public class Person {

		private Integer idPerson;
		private String name;
		private LocalDate birthdate;
		
		public Person() {
			super();
		}
		
		public Person(Integer idPerson, String name, LocalDate birthdate) {
			super();
			this.idPerson = idPerson;
			this.name = name;
			this.birthdate = birthdate;	
		}
		
		public Person(Integer idPerson, String name) {
			this(idPerson, name, null);	
		}
		
		public Person(String name) {
			this(null, name, null);	
		}
		
		public Person(String name, LocalDate birthdate) {
			this(null, name, birthdate);	
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_person")
		public Integer getIdPerson() {
			return idPerson;
		}

		public void setIdPerson(Integer idPerson) {
			this.idPerson = idPerson;
		}

		@Column(nullable = false, length = 255)
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDate getBirthdate() {
			return birthdate;
		}

		public void setBirthdate(LocalDate birthdate) {
			this.birthdate = birthdate;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder(name);
			return builder.append(" (")
			.append(Objects.toString(birthdate, "unknown"))
			.append(')')
			.append(" ")
			.append('#')
			.append(idPerson)
			.toString();
		}
		
	
}
