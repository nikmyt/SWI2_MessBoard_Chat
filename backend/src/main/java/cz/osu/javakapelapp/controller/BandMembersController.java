package cz.osu.javakapelapp.controller;

import cz.osu.javakapelapp.model.BandMember;
import cz.osu.javakapelapp.service.BandMemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandMembersController {
	private BandMemberService bandMemberService;

	public BandMembersController(BandMemberService bandMemberService) {
		this.bandMemberService = bandMemberService;
	}

	@PostMapping("/band-members")
	public BandMember create(@Valid @RequestBody BandMember newBandMember){
		return bandMemberService.create(newBandMember);
	}

}
