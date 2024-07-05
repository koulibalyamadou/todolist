package com.odc.todolist.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Random;

@Entity
public class Carre extends Figure{

    private float cote;

    public Carre(){
        super(Math.abs(new Random().nextInt()), "CARRE");
    }

    public float getCote(){
        return cote;
    }

    public void setCote(float cote){
        this.cote = cote;
    }

    @Override
    public String toString() {
        return "Carre{" +
                "id=" + getId() +
                "nom=" + getNom() +
                "cote=" + cote +
                '}';
    }

    @Override
    public float determinerAire() {
        return cote*cote;
    }

    @Override
    public float determinerPerimetre() {
        return 4*cote;
    }
}
