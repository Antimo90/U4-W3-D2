package antimomandorino.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "titles", nullable = false, length = 30)
    private String title;
    @Column(name = "date_events", nullable = false)
    private LocalDate date_event;
    @Column(name = "descriptions", nullable = false)
    private String description;
    @Column(name = "types", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;
    @Column(name = "maximum_number_of_participants", nullable = false)
    private int maximum_number;

    public Event() {
    }

    public Event(String title, LocalDate date_event, String description, EventType type, int maximum_number) {
        this.title = title;
        this.date_event = date_event;
        this.description = description;
        this.type = type;
        this.maximum_number = maximum_number;
    }

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDate date_event) {
        this.date_event = date_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getMaximum_number() {
        return maximum_number;
    }

    public void setMaximum_number(int maximum_number) {
        this.maximum_number = maximum_number;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date_event=" + date_event +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", maximum_number=" + maximum_number +
                '}';
    }
}
