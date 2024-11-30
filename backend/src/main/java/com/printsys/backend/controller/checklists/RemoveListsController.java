package com.printsys.backend.controller.checklists;

import com.printsys.backend.service.checklists.RemoveListsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveListsController {

  @Autowired
  private RemoveListsService removeListsService;

  @PostMapping("/checklists/remove/")
  public Map<String, String> removeLists(@RequestParam Map<String, String> data) {
    return removeListsService.removeLists(data);
  }

}
