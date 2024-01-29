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
    private Long destinationId;

    private String name;

    public Destination(String destination)
    {
        this.name = destination;
    }

    public String getName() {
        return name;
    }
    public Long getDestinationId()
    {
        return this.destinationId;
    }
    public void setName(String name) {
        this.name = name;
    }
}
