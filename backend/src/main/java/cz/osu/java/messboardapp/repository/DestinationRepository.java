package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d FROM Destination d WHERE LOWER(d.name) LIKE %:name%")
    List<Destination> findDestinationsByNameContainingIgnoreCase(@Param("name") String name);
    Destination findByDestinationId(Long destinationId);
    Destination findTopByNameOrderByDestinationIdDesc(String name);

    Destination findDestinationByName(String name);
}
