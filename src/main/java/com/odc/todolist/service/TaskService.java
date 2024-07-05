package com.odc.todolist.service;

import com.odc.todolist.model.Task;
import com.odc.todolist.repository.TaskRepoository;
import com.odc.todolist.repository.KUtils;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class TaskService {

    private final TaskRepoository taskRepoository;

    public TaskService(TaskRepoository taskRepoository) {
        this.taskRepoository = taskRepoository;
    }

    public void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Ajout d'une nouvelle tache" );

        System.out.print( "Nom : " );
        String nom = scanner.nextLine();

        System.out.print( "Description : " );
        String description = scanner.nextLine();

        Task task = new Task();
        task.setName(nom);
        task.setDescription(description);
        task.setPriority(KUtils.randomTaskPriority());
        task.setImage(KUtils.createImage());
        task.setDone(new Random().nextBoolean());

        taskRepoository.save(task);
    }

    public void modify(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Modification d'une tache" );

        System.out.print( "Donnez l'id : " );
        int id = scanner.nextInt();

        Task task = taskRepoository.findById(id).orElse(null);
        if( task == null )
            System.err.println( "Task not found" );

        System.out.println( "Nom : " );
        String nom = scanner.nextLine();
        task.setName(nom);

        System.out.println( "Description : " );
        String description = scanner.nextLine();
        task.setDescription(description);

        taskRepoository.save(task);
    }

    public void search(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Recherche d'une tache" );

        System.out.print( "Donnez l'id : " );
        int id = scanner.nextInt();

        Task task = taskRepoository.findById(id).orElse(null);
        if( task == null )
            System.err.println( "Task not found" );

        System.out.println(task);
    }

    public void remove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Suppression d'une tache" );

        System.out.print( "Donnez l'id : " );
        int id = scanner.nextInt();

        Task task = taskRepoository.findById(id).orElse(null);
        if( task == null )
            System.err.println( "Task not found" );

        taskRepoository.delete(task);
    }
}
