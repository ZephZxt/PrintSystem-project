package com.printsys.backend.controller.checklists;

import com.printsys.backend.service.checklists.UpdateListStateService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateListStateController {

  @Autowired
  private UpdateListStateService updateListStateService;

  @PostMapping("/checklists/update/state/")
  public Map<String, String> updateListState(@RequestParam Map<String, String> data) {
    return updateListStateService.updateListState(data);
  }

}
