package com.example.messboard.Model.db;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoardUser //BOARDUSER / boarduser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ideálně uuid -> vytváří komplexní id
    @Column(name="user_id", nullable = false)
    private Integer userId; //user_id / USER_ID
    @Column(nullable = false, length=100)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String e_mail;












}
