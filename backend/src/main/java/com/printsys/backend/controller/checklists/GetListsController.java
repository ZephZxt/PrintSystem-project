package com.printsys.backend.controller.checklists;

import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.GetListsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetListsController {

  @Autowired
  private GetListsService getListsService;

  @GetMapping("/checklists/get/")
  public List<Checklists> getLists() {
    return getListsService.getLists();
  }

}
