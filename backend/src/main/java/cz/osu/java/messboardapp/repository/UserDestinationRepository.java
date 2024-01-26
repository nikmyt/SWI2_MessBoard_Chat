package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.UserDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDestinationRepository extends JpaRepository<UserDestination, Integer> {

    // Custom query method to find all destinationIds for a specific userId
    @Query("SELECT ud.id.destinationId FROM UserDestination ud WHERE ud.id.userId = :userId")
    List<Long> findDestinationIdsByUserId(@Param("userId") Long userId);
}
