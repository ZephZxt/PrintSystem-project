package com.printsys.backend.controller.checklists;

import com.printsys.backend.service.checklists.AddListsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddListsController {

  @Autowired
  private AddListsService addListsService;

  @PostMapping("/checklists/add/")
  public Map<String, String> addLists(@RequestParam Map<String, String> data) {
    return addListsService.addLists(data);
  }

}
