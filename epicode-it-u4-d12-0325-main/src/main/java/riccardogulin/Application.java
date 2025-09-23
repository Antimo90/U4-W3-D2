package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.entities.StudentType;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	// Per connetterci al database dobbiamo aggiungere nella classe main una EntityManagerFactory
	// Dobbiamo passargli il nome della persistence unit che abbiamo configurato nel persistence.xml
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d12pu");


	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager(); // Oggetto che gestirà tutte le operazioni con il database
		StudentsDAO sd = new StudentsDAO(em);


		Student aldo = new Student("Aldo", "Baglio", StudentType.FRONTEND);
		Student giovanni = new Student("Giovanni", "Storti", StudentType.BACKEND);
		Student giacomo = new Student("Giacomo", "Poretti", StudentType.FULLSTACK);
		// Un oggetto nuovo di zecca non fa parte degli oggetti gestiti dall'Entity Manager (NON E'MANAGED)
		// Per poter essere salvato nel DB dovrà far parte del Persistence Context (memoria che contiene gli oggetti MANAGED)
		// Tramite il metodo .persist() dell'Entity Manager possiamo rendere un nuovo oggetto MANAGED
		// Un oggetto può anche essere MANAGED se lo leggo direttamente dal DB tramite un'operazione tipo find
		// MANAGED NON VUOL DIRE ANCORA SALVATO NEL DB!
		// MANAGED significa che se vado a modificare questo oggetto (es. cambio nome tramite setter), il Persistence Context terrà traccia
		// di tali modifiche. Quando vorrò potrò chiedere al Persistence Context di "sincronizzare" tali modifiche con la riga corrispondente nella tabella
		// Se è un oggetto appena creato non ci sarà ancora una riga nella tabella e di conseguenza verrà aggiunta
		// Se l'oggetto invece corrisponde già una riga della tabella, quella riga verrà aggiornata

		sd.save(giovanni);
		// sd.save(giacomo);

		// ******************************************** FIND BY ID **************************************
		try {
			Student aldoFromDB = sd.findById(6);
			System.out.println(aldoFromDB);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		// ******************************************** FIND BY ID AND DELETE **************************************
		try {
			sd.findByIdAndDelete(2);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		// Best Practice: quando finisco di utilizzare delle risorse (come scanner o entity manager) sempre bene chiuderle
		em.close();
		emf.close();
	}


}
