package com.odc.todolist.model;

import jakarta.persistence.Entity;

@Entity
public class TriangleEquilaterale extends Triangle{

    public TriangleEquilaterale(float a) {
        super(a, a, a);
        setNom("TRIANGLE EQUILATERALE");
    }

    @Override
    public float determinerAire() {
        return (a*a)/2;
    }

    @Override
    public float determinerPerimetre() {
        return 3*a;
    }
}
