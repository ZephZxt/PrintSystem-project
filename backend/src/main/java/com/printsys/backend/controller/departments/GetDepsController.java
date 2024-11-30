package com.printsys.backend.controller.departments;

import com.printsys.backend.pojo.Departments;
import com.printsys.backend.service.departments.GetDepsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDepsController {

  @Autowired
  private GetDepsService getDepsService;

  @GetMapping("/departments/get/")
  public List<Departments> getDeps() {
    return getDepsService.getDeps();
  }



}
