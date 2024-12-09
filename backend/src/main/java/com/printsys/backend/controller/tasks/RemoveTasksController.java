package com.printsys.backend.controller.tasks;

import com.printsys.backend.service.tasks.RemoveTasksService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveTasksController {

  @Autowired
  private RemoveTasksService removeTasksService;

  @PostMapping("/tasks/remove/")
  public Map<String, String> removeTasks(@RequestParam Map<String, String> data) {
    return removeTasksService.removeTasks(data);
  }

}
