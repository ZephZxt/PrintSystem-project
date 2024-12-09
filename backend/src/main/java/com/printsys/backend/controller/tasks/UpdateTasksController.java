package com.printsys.backend.controller.tasks;

import com.printsys.backend.service.tasks.UpdateTasksService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateTasksController {

  @Autowired
  private UpdateTasksService updateTasksService;

  @PostMapping("/tasks/update/")
  public Map<String, String> updateTasks(@RequestParam Map<String, String> data) {
    return updateTasksService.updateTasks(data);
  }

}
