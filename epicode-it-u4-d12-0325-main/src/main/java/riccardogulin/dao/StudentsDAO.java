package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class StudentsDAO {
	// DAO (Data Access Object) è un Design Pattern. Questa classe serve per semplificare le interazioni con il database. Conterrà tutta una serie
	// di metodi personalizzati ben più semplici di quelli che fornisce l'Entity Manager. Questo significa nascondere le complessità dovute all'
	// utilizzo dell'EntityManager fornendo dei metodi più intuitivi e diretti a chi dovrà interagire con la tabella

	private final EntityManager entityManager;

	public StudentsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Student newStudent) {

		// 1. Chiediamo all'entity manager di creare una nuova transazione
		EntityTransaction transaction = entityManager.getTransaction();

		// 2. Facciamo partire la transazione
		transaction.begin();

		// 3. Aggiungiamo il newStudent al Persistence Context tramite metodo .persist() dell'Entity Manager (l'oggetto newStudent diventa MANAGED ma
		// non è ancora parte del database)
		entityManager.persist(newStudent);

		// 4. Per rendere effettivamente newStudent una nuova riga della tabella, bisogna fare un'operazione di commit tramite la transazione
		transaction.commit();

		// 5. Log di avvenuto salvataggio
		System.out.println("Lo studente " + newStudent.getSurname() + " è stato salvato correttamente!");

	}

	public Student findById(long studentId) {
		Student found = entityManager.find(Student.class, studentId); // Devo specificargli in quale tabella (entity) andare a cercare, e qual è l'id da cercare
		if (found == null) throw new NotFoundException(studentId);
		return found;
	}

	public void findByIdAndDelete(long studentId) {

		// 1. Cerco lo studente nel database tramite id
		Student found = this.findById(studentId);

		// 2. Chiediamo all'entity manager di creare una nuova transazione
		EntityTransaction transaction = entityManager.getTransaction();

		// 3. Facciamo partire la transazione
		transaction.begin();

		// 4. Rimuoviamo lo studente dal Persistence Context tramite metodo .remove() dell'Entity Manager (l'oggetto a questo punto non è ancora rimosso
		// dal db
		entityManager.remove(found);

		// 5. Per rimuovere effettivamente lo studente dalla tabella, bisogna fare un'operazione di commit tramite la transazione
		transaction.commit();

		// 5. Log di avvenuto salvataggio
		System.out.println("Lo studente " + found.getSurname() + " è stato rimosso correttamente!");
	}
}
