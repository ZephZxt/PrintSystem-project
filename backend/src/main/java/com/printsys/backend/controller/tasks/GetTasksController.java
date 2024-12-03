package com.printsys.backend.controller.tasks;

import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.service.tasks.GetTasksService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTasksController {

  @Autowired
  private GetTasksService getTasksService;

  @GetMapping("/tasks/get/")
  public List<Tasks> getTasks(@RequestParam Map<String, String> data) {
    return getTasksService.getTasks(data);
  }

}
