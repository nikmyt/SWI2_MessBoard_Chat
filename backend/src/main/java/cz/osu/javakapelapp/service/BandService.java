package cz.osu.javakapelapp.service;

import cz.osu.javakapelapp.model.Band;

import java.util.List;

//we do DInjection here!
public interface BandService {
	//crud+l
	Band create(Band newBand);
	Band get(long id);
	void update(Band band) throws Exception;
	void delete(long id) throws Exception;
	List<Band> getAll();
	List<Band> search(String name);
}
