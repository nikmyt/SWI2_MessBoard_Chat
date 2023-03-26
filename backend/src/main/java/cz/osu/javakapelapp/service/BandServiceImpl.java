package cz.osu.javakapelapp.service;

import cz.osu.javakapelapp.exception.RecordNotFoundException;
import cz.osu.javakapelapp.model.Band;
import cz.osu.javakapelapp.repository.BandRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//use the shit out of alt+insert here
//poslední vrstva před controller
@Service
public class BandServiceImpl implements BandService{

	private BandRepository bandRepository;

	public BandServiceImpl(BandRepository bandRepository) {
		this.bandRepository = bandRepository;
	}

	@Override
	public Band create(Band newBand) {
		newBand.setTimeCreated(LocalDateTime.now());
		Band ret = bandRepository.save(newBand);
		return ret;
	}

	@Override
	public Band get(long id) {
		//return bandRepository.getReferenceById(id); //this is bad!
		return bandRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Band not found."));
	}

	@Override
	public void update(Band band) throws Exception {
	//Band fromDb = bandRepository.getReferenceById(band.getId());
	Band fromDb = get(band.getId());
	if(fromDb != null){
		fromDb.setName(band.getName());
		fromDb.setTimeCreated(band.getTimeCreated());
		fromDb.setBandMembers(band.getBandMembers());
		fromDb.setFounded(band.getFounded());
		bandRepository.save(fromDb); //band or fromdb?
	} else {
		throw new RecordNotFoundException("Band not found!"); //altinsert
	}
	}

	@Override
	public void delete(long id) throws Exception {
	boolean exists = bandRepository.existsById(id);
	if (exists){
		bandRepository.deleteById(id);
	} else {
		throw new RecordNotFoundException("Band not found!");
	}
	}

	@Override
	public List<Band> getAll() {
		return bandRepository.findAll();
	}

	//wew epic!!!
	@Override
	public List<Band> search(String name){
		return bandRepository.findAllByNameContainsIgnoreCase(name);
	}
}
