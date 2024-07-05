package com.odc.todolist.dto;

import com.odc.todolist.enums.TaskPriority;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class TaskDTO {

    @NotEmpty(message = "Nom de la tache requise")
    private String name;

    @NotEmpty(message = "La description est requise")
    @Size(min = 10, message = "Description trop courte")
    @Size(max = 30, message = "Description trop longue")
    private String description;

    private MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
