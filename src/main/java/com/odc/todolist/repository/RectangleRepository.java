package com.odc.todolist.repository;

import com.odc.todolist.model.Carre;
import com.odc.todolist.model.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle, Integer> {
}
