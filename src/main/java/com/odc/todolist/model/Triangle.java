package com.odc.todolist.model;

import jakarta.persistence.MappedSuperclass;

import java.util.Random;

@MappedSuperclass
public abstract class Triangle extends Figure{

    protected float a, b, c;

    public Triangle(float a, float b, float c){
        super(Math.abs(new Random().nextInt()), "TRIANGLE");
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
