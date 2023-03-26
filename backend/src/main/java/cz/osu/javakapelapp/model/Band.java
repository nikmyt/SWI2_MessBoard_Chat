package cz.osu.javakapelapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//could be M:N cause many guys can be in many bands

//this shits getting really annyoing. i can't see my db in the sidebar

@Entity
@Table(name = "bands")
public class Band {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String name;

	private LocalDate founded;

	private LocalDateTime timeCreated;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "band", fetch = FetchType.EAGER)
	private List<BandMember> bandMembers;

	public Band(){
		bandMembers = new ArrayList();
	}

	public Band(long id, String name, LocalDate founded, LocalDateTime timeCreated, List<BandMember> bandMembers) {
		this.id = id;
		this.name = name;
		this.founded = founded;
		this.timeCreated = timeCreated;
		this.bandMembers = bandMembers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getFounded() {
		return founded;
	}

	public void setFounded(LocalDate founded) {
		this.founded = founded;
	}

	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}

	public List<BandMember> getBandMembers() {
		return bandMembers;
	}

	public void setBandMembers(List<BandMember> bandMembers) {
		this.bandMembers = bandMembers;
	}
}
