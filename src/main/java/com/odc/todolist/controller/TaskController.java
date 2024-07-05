package com.odc.todolist.controller;

import com.odc.todolist.dto.TaskDTO;
import com.odc.todolist.model.Task;
import com.odc.todolist.repository.TaskRepoository;
import com.odc.todolist.repository.KUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

@Controller
public class TaskController {

    @Autowired
    private TaskRepoository taskRepoository;

    @GetMapping("/tasks")
    public String index(Model model){
        model.addAttribute("tasks", taskRepoository.findAll(Sort.by(Sort.Direction.DESC, "id" )) );
        return "tasks/index";
    }

    @GetMapping("/tasks/new")
    public String getCreate(Model model){
        model.addAttribute("taskDTO", new TaskDTO() );
        return "tasks/new";
    }

    @PostMapping("/tasks/new")
    public String postCreate(@Valid @ModelAttribute TaskDTO taskDTO, BindingResult bindingResult){
        if(taskDTO.getImage().isEmpty()){
            bindingResult.addError(new FieldError("taskDTO", "image", "Image requise"));
        }
        if( bindingResult.hasErrors() ){
            return "tasks/new";
        }

        //Save Image File Name
        MultipartFile multipartFile = taskDTO.getImage();
        String storageFilename = new Date().getTime() + "_" + multipartFile.getOriginalFilename();

        try {
            String uploadedDir = "public/images/";
            Path uploadedPath = Paths.get(uploadedDir);

            if (!Files.exists(uploadedPath)) {
                Files.createDirectories(uploadedPath);
            }

            try(InputStream inputStream = multipartFile.getInputStream() ){
                Files.copy(inputStream, Paths.get( uploadedDir + storageFilename ), StandardCopyOption.REPLACE_EXISTING );
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setImage(storageFilename);
        task.setDone(new Random().nextBoolean());
        task.setPriority(KUtils.randomTaskPriority());

        taskRepoository.save(task );

        return "redirect:/tasks";
    }

    @GetMapping("tasks/edit")
    public String getEdit(Model model, @RequestParam int id){

        try{
            Task task = taskRepoository.findById(id).orElse(null);
            if(task != null){
                model.addAttribute("task", task);

                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setDescription(task.getDescription());
                taskDTO.setName(task.getName());
                //taskDTO.setImage(task.getImage());

                model.addAttribute("taskDTO", taskDTO);
            }
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return "redirect:/tasks";
        }

        return "tasks/edit";
    }

    @PostMapping("tasks/edit")
    public String postEdit(Model model, @Valid @ModelAttribute TaskDTO taskDTO, @RequestParam int id, BindingResult bindingResult){
        try{
            Task task = taskRepoository.findById(id).orElse(null);
            model.addAttribute("task", task);

            if( bindingResult.hasErrors() )
                return "tasks/edit";

            if( !taskDTO.getImage().isEmpty() ){
                //delete old image
                String uploadedDir = "public/images/";
                Path oldImagePath = Paths.get(uploadedDir + task.getImage() );

                try{
                    Files.deleteIfExists(oldImagePath);
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }

                //save new image
                MultipartFile image = taskDTO.getImage();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
                System.err.println("Original file name is : " + image.getOriginalFilename() );

                try(InputStream inputStream = image.getInputStream() ){
                    Files.copy(inputStream, Paths.get( uploadedDir + storageFileName ), StandardCopyOption.REPLACE_EXISTING );
                }
                task.setImage(storageFileName);
            }
            task.setName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());

            taskRepoository.save(task);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete")
    public String getDelete(@RequestParam int id){
        taskRepoository.delete(taskRepoository.findById(id).orElse(null));
        return "redirect:/tasks";
    }


}
