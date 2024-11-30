package com.printsys.backend.controller.departments;


import com.printsys.backend.service.departments.AddDepsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddDepsController {

  @Autowired
  private AddDepsService addDepsService;

  @PostMapping("/departments/add/")
  public Map<String, String> addDeps(@RequestParam Map<String, String> data) {
    return addDepsService.addDeps(data);
  }

}
