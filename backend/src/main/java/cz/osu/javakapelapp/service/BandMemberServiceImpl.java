package cz.osu.javakapelapp.service;

import cz.osu.javakapelapp.exception.RecordNotFoundException;
import cz.osu.javakapelapp.model.Band;
import cz.osu.javakapelapp.model.BandMember;
import cz.osu.javakapelapp.repository.BandMemberRepository;
import cz.osu.javakapelapp.repository.BandRepository;
import org.springframework.stereotype.Service;

@Service
public class BandMemberServiceImpl implements BandMemberService{

	private BandMemberRepository bandMemberRepository;
	private BandRepository bandRepository;

	public BandMemberServiceImpl(BandMemberRepository bandMemberRepository, BandRepository bandRepository) {
		this.bandMemberRepository = bandMemberRepository;
		this.bandRepository = bandRepository;
	}

	@Override
	public BandMember create(BandMember newBandMember) {
		Band band = bandRepository.findById(newBandMember.getBand().getId()).orElseThrow(() -> new RecordNotFoundException("Band not found"));

		newBandMember.setBand(band);
		return bandMemberRepository.save(newBandMember);
	}

	//public
}
