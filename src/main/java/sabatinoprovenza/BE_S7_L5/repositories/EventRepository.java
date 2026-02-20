package sabatinoprovenza.BE_S7_L5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabatinoprovenza.BE_S7_L5.entities.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
