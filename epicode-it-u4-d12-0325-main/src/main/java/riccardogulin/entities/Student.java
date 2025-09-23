package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // Qua dichiariamo che questa classe dovrà essere mappata ad una specifica tabella nel db
// Sarà Hibernate a creare la tabella se questa non esiste oppure proverà a modificare una tabella esistente se nota che la struttura non corrisponde
// ma solo se abbiamo aggiunto <property name="hibernate.hbm2ddl.auto" value="update"/> al persistence.xml
@Table(name = "students") // <-- Annotazione OPZIONALE. Serve per personalizzare il nome della tabella
public class Student {

	@Id // <-- Annotazione OBBLIGATORIA!!! Serve per dire a JPA che questo attributo corrisponderà alla colonna chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Annotazione OPZIONALE ma molto consigliata. Serve per chiedere al DB di gestire lui
	// la creazione dei valori per la PK. Se non usassi @GeneratedValue, dovrei essere io ad ogni nuovo record a dover inserire un id manualmente
	// IDENTITY chiede al DB di rendere questo long un BIG SERIAL
	private long id;
	@Column(name = "nome", nullable = false, length = 30)
	// <-- Annotazione OPZIONALE. Serve per personalizzare la colonna, quindi posso customizzare il nome della colonna oppure aggiungere
	// dei vincoli tipo UNIQUE, NON-NULL oppure anche ad esempio una lunghezza massima del testo
	private String name;
	@Column(name = "cognome", nullable = false, length = 30)
	private String surname;
	@Enumerated(EnumType.STRING) // <-- OBBLIGATORIA quando abbiamo a che fare con gli enum perché di default vengono trattati come numeri, invece
	// noi vogliamo che corrispondano a campi testuali
	@Column(name = "tipo_studente")
	private StudentType type;

	public Student() {
	} // OBBLIGATORIO AVERE UN COSTRUTTORE VUOTO NELLE ENTITIES! Serve per JPA per ritornarci degli oggetti quando leggiamo righe dalla tabella

	public Student(String name, String surname, StudentType studentType) {
		this.name = name;
		this.surname = surname;
		this.type = studentType;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public StudentType getType() {
		return type;
	}

	public void setType(StudentType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", type=" + type +
				'}';
	}
}
