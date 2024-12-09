package com.printsys.backend.controller.tasks;

import com.printsys.backend.service.tasks.UpdateStateService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStateController {

  @Autowired
  private UpdateStateService updateStateService;

  @PostMapping("/tasks/update/state/")
  public Map<String, String> updateState(@RequestParam Map<String, String> data) {
    return updateStateService.updateState(data);
  }

}
