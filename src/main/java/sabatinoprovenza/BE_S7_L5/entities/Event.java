package sabatinoprovenza.BE_S7_L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int maxSeats;
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    public Event(String title, String description, LocalDate date, String location, int maxSeats, User organizer) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.maxSeats = maxSeats;
        this.organizer = organizer;
    }
}
