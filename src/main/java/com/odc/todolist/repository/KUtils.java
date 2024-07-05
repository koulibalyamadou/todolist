package com.odc.todolist.repository;

import com.odc.todolist.model.Task;
import com.odc.todolist.enums.TaskPriority;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class KUtils {

    public static final String EXTENSION = ".webp";
    public static final String BEGIN = "https://cdn.dummyjson.com/recipe-images/";

    private List<Task> tasks = new ArrayList<>();

    public void init(int size){
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    Math.abs(new Random().nextInt()),
                    generateRandomString(10),
                    generateRandomString(40),
                    createImage(),
                    randomTaskPriority(),
                    new Random().nextBoolean()
            );
            tasks.add( task );
        }
    }

    public static List<Task> initReturn(int size){
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    Math.abs(new Random().nextInt()),
                    generateRandomString(10),
                    generateRandomString(40),
                    createImage(),
                    randomTaskPriority(),
                    new Random().nextBoolean()
            );
            tasks.add( task );
        }
        return tasks;
    }

    public void add(Task task){
        tasks.add(task);
    }

    public void filterByPriority(TaskPriority priority){tasks.stream().filter( t -> t.getPriority() == priority ).toList().forEach( t -> System.out.println(t) );}

    public List<Task> filterByPriorityReturn(TaskPriority priority){
        return tasks.stream().filter( t -> t.getPriority() == priority ).toList();
    }

    public void filterByDone(boolean done){tasks.stream().filter( t -> t.isDone() == done ).toList().forEach( t -> System.out.println(t) );}

    public List<Task> filterByDoneReturn(boolean done){return tasks.stream().filter( t -> t.isDone() == done ).toList();}

    public void search(int id){tasks.stream().filter( t -> t.getId() == id ).toList().forEach( t -> System.out.print(t) );}

    public Task searchReturn(int id){return tasks.stream().filter( t -> t.getId() == id ).findFirst().get();}

    public void remove(int id){
        this.tasks = tasks.stream().filter( t -> t.getId() != id ).toList();
    }

    public void seeAlls(){
        tasks.forEach(t -> System.out.println(t));
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static TaskPriority randomTaskPriority(){
        int priority = (int) (((Math.random()*30) + 1)/3);

        TaskPriority taskPriority = null;
        if( priority == 1 ) taskPriority = TaskPriority.FAIBLE;
        else if( priority == 2 ) taskPriority = TaskPriority.MOYENNE;
        else taskPriority = TaskPriority.HAUTE;

        return taskPriority;
    }

    public static String createImage(){
        int image = (int) ((Math.random() * 30) + 1);
        return BEGIN + image + EXTENSION;
    }
}
