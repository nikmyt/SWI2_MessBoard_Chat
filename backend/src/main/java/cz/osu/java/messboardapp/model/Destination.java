package cz.osu.java.messboardapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;

    public Destination(String destination)
    {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
    public Long getId()
    {
        return this.id;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
}
