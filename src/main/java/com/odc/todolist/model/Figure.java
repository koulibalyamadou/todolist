package com.odc.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Figure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public Figure(int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public abstract float determinerAire();
    public abstract float determinerPerimetre();
}
