package com.odc.todolist.service;

import com.odc.todolist.model.Carre;
import com.odc.todolist.model.Figure;
import com.odc.todolist.repository.CarreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class FigureService {

    @Autowired
    private CarreRepository carreRepository;

    public void addCarre(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Donnez le cote du carré" );
        float cote = scanner.nextFloat();

        Carre carre = new Carre();
        carre.setCote(cote);
        carreRepository.save(carre);
    }

    public void searchCarre(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Donnez le id du carre a rechercher ");
        int id = scanner.nextInt();

        Carre carre = carreRepository.findById(id).orElse(null);
        if( carre == null ) {
            System.err.println( "Carre introuvable" );
            return;
        }
        System.out.println(carre);
    }

}
