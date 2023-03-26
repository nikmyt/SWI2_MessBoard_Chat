package cz.osu.javakapelapp.controller;

import cz.osu.javakapelapp.model.Band;
import cz.osu.javakapelapp.service.BandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//wew, thanks to DI this is just fine.

//now this is a doozy
//post = url adress
//valid = exists, is good, were not putting poo in there etc
//requestbody = thru post method, this is it's body

@RestController
@RequestMapping("/bands")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BandsController {

	@Autowired
	private BandService bandService;

	public BandsController(BandService bandService) {
		this.bandService = bandService;
	}

	@PostMapping("/bands") // /new
	public Band create(@Valid @RequestBody Band newBand){
		return bandService.create(newBand);
	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<Band> getAll(){
		return bandService.getAll();
	}


	//this is for specific id band. k
	@GetMapping("/bands/{id}")
	public Band get(@PathVariable("id") long id){
		return bandService.get(id);
	}

	// %20 is a space in a url
	//possibly in the future: search?name=l&year=2005
	@GetMapping("/bands/search/{name}")
	public List<Band> search(@PathVariable("name") String name){
		return bandService.search(name);
	}

}

//postman test. dont forget to turn on server. i think u dont need xamp
//http:localhost:8080/bands
//http:localhost:3306/bands?
// http://localhost:8080/bands/
//v body dej raw: {"name":"Black Sabbath"}

// and add header: Content-Type: application/json