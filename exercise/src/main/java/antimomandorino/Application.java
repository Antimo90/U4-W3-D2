package antimomandorino;

import antimomandorino.dao.EventDAO;
import antimomandorino.entities.Event;
import antimomandorino.entities.EventType;
import antimomandorino.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d12pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EventDAO ed = new EventDAO(em);

        Event concert = new Event("Concerto Rock", LocalDate.of(2025, 12, 15), "Un grande concerto con band famose.", EventType.PUBLIC, 500);
        Event conference = new Event("Conferenza sulla IA", LocalDate.of(2026, 3, 22), "Conferenza per esplorare le ultime novità sull'intelligenza artificiale.", EventType.PUBLIC, 150);
        Event workshop = new Event("Workshop di pittura", LocalDate.of(2025, 11, 5), "Impara le tecniche base della pittura ad olio.", EventType.PRIVATE, 20);

        // ed.save(concert);
        //  ed.save(conference);
        // ed.save(workshop);

        try {
            Event concertFromDB = ed.findById(1);
            System.out.println(concertFromDB);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            ed.delete(2);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        em.close();
        emf.close();
    }
}
