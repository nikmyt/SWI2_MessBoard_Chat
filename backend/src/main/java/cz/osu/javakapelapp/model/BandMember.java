package cz.osu.javakapelapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
//import javax.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "band_members") //its ok
public class BandMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;
	@NotNull
	private String roles; //instruments played
	@ManyToOne
	@JoinColumn(name = "band_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Band band;

	public BandMember() {
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}
}
