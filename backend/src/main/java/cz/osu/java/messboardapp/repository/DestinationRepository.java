package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByNameContainingIgnoreCase(String searchTerm);
    Destination findByDestinationId(Long destinationId);
    Destination findTopByNameOrderByDestinationIdDesc(String name);
}
