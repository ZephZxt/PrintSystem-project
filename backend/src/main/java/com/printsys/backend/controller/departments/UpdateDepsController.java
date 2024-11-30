package com.printsys.backend.controller.departments;

import com.printsys.backend.service.departments.UpdateDepsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateDepsController {

  @Autowired
  private UpdateDepsService updateDepsService;

  @PostMapping("/departments/update/")
  public Map<String, String> updateDeps(@RequestParam Map<String, String> data) {
    return updateDepsService.updateDeps(data);
  }

}
