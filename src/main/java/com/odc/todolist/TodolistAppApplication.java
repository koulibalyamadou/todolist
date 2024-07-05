package com.odc.todolist;

import com.odc.todolist.service.FigureService;
import com.odc.todolist.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class TodolistAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FigureService figureService){
		return args -> {

			figureService.searchCarre();

			//figureService.addCarre();
			//figureService.addCarre();

			/*List<Task> list = TaskRepoositoryPro.initReturn(10);
			list.forEach(new Consumer<Task>() {
				@Override
				public void accept(Task task) {
					taskRepoository.save(task);
					System.out.println(task);
				}
			});*/


			//taskService.remove();

			//askQuestion(taskService);

			//taskService.add();

			/*Task task = taskRepoository.findById(1).orElseThrow(()->new NullPointerException() );

			if (task != null) {
				System.out.println("**********************************************");
				System.out.println(task);
				System.out.println("**********************************************");
			}else{
				System.out.println("Tache introuvable");
			}

			task.setName("Une tache qui ate modifie");
			task.setDescription("Une nouvelle tache modifie");
			taskRepoository.save(task);

			System.out.println("************************Apres modification****************************");
			System.out.println(task);*/

			//taskRepoository.findAll().forEach(System.out::println);
		};
	}

	public void askQuestion(TaskService taskService){
		int choix = 0;
		Scanner scanner = new Scanner(System.in);
		do{
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1- Ajouter" );
			System.out.println("2- Rechercher" );
			System.out.println("3- Modifier" );
			System.out.println("4- Supprimer" );
			System.out.println("5- Quitter" );
			System.out.println("----------------------------------------------------------------------------");

			System.out.print( "Choix : " );
			choix = scanner.nextInt();

			switch (choix){
				case 1 : {
					taskService.add();
					break;
				}
				case 2 : {
					taskService.search();
					break;
				}
				case 3 : {
					taskService.modify();
					break;
				}
				case 4 : {
					taskService.remove();
				}
			}
		}while (choix != 5);
	}

}
