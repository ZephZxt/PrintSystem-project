package com.printsys.backend.controller.departments;

import com.printsys.backend.service.departments.RemoveDepsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveDepsController {

  @Autowired
  private RemoveDepsService removeDepsService;

  @PostMapping("/departments/remove/")
  public Map<String, String> removeDeps(@RequestParam Map<String, String> data) {
    return removeDepsService.removeDeps(data);
  }

}
