package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.UserDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDestinationRepository extends JpaRepository<UserDestination, Integer> {
    List<Long> findDestinationIdsByUserId(@Param("userId") Long userId);
}