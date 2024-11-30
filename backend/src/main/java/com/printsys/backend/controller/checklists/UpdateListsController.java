package com.printsys.backend.controller.checklists;

import com.printsys.backend.service.checklists.UpdateListsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateListsController {

  @Autowired
  private UpdateListsService updateListsService;

  @PostMapping("/checklists/update/")
  public Map<String, String> updateLists(@RequestParam Map<String, String> data) {
    return updateListsService.updateLists(data);
  }

}
