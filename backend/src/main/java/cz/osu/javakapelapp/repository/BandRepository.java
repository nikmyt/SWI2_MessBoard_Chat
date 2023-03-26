package cz.osu.javakapelapp.repository;

import cz.osu.javakapelapp.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BandRepository extends JpaRepository<Band, Long> {
	//"mám možnost udělat veškeré --gradle-- CRUDL operace"
	//"dependency injection"

	List<Band> findAllByNameContainsIgnoreCase(String name); //woahh it does what it do by name!


}
