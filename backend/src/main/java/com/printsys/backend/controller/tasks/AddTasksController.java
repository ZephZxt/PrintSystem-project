package com.printsys.backend.controller.tasks;

import com.printsys.backend.service.tasks.AddTasksService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddTasksController {

  @Autowired
  private AddTasksService addTasksService;

  @PostMapping("/tasks/add/")
  public Map<String, String> addTask(@RequestParam Map<String, String> data) {
    System.out.println(data);
    return addTasksService.addTasks(data);
  }

}
