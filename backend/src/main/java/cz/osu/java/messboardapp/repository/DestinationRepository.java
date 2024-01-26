package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    List<Destination> findByDestinationContainingIgnoreCase(String searchTerm);
    Destination findById(Long destinationId);
}
