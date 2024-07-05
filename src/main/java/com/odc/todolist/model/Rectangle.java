package com.odc.todolist.model;

import jakarta.persistence.Entity;

import java.util.Random;

@Entity
public class Rectangle extends Figure{

    private float longueur;
    private float largeur;

    public Rectangle(float L, float l){
        super(Math.abs(new Random().nextInt()), "CARRE");
        this.longueur = L;
        this.largeur = l;
    }

    @Override
    public float determinerAire() {
        return longueur * largeur;
    }

    @Override
    public float determinerPerimetre() {
        return 2*(longueur + largeur);
    }
}
