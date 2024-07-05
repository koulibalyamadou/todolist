package com.odc.todolist.model;

import jakarta.persistence.Entity;

@Entity
public class TriangleIsocele extends Triangle{

    public TriangleIsocele(float base, float cote) {
        super(base, cote, cote);
        setNom("TRIANGLE ISOCELE");
    }

    @Override
    public float determinerAire() {
        return (a*b)/2;
    }

    @Override
    public float determinerPerimetre() {
        return 2*b+a;
    }
}
