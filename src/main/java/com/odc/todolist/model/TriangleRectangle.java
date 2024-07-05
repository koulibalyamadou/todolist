package com.odc.todolist.model;

import jakarta.persistence.Entity;

@Entity
public class TriangleRectangle extends Triangle{

    public TriangleRectangle(float base, float hauteur, float hypotenuse) {
        super(base, hauteur, hypotenuse);
        setNom("TRIANGLE RECTANGLE");
    }

    @Override
    public float determinerAire() {
        return (a*b)/2;
    }

    @Override
    public float determinerPerimetre() {
        return a+b+c;
    }
}
