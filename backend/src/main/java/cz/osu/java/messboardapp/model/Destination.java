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

    private String Name;

    public Destination(String destination)
    {
        this.Name = destination;
    }

    public String getName() {
        return Name;
    }
    public Long getId()
    {
        return this.id;
    }
    public void setName(String name) {
        this.Name = name;
    }
}
