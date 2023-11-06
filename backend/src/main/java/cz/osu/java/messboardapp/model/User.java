package cz.osu.java.messboardapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Table(name = "user")
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ideálně uuid -> vytváří komplexní id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;
}
