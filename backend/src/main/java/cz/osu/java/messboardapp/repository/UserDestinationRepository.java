package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.UserDestination;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDestinationRepository extends JpaRepository<UserDestination, Integer> {

    // Custom query method to find all destinationIds for a specific userId
    @Query("SELECT ud.id.destinationId FROM UserDestination ud WHERE ud.id.userId = :userId")
    List<Long> findDestinationIdsByUserId(@Param("userId") Long userId);

    @Query("SELECT ud FROM UserDestination ud WHERE ud.id.userId = :userId AND ud.id.destinationId = :destinationId")
    UserDestination findUserDestinationByIdUserIdAndIdDestinationId(Long userId, Long destinationId);

}
