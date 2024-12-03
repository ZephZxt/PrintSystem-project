package com.printsys.backend.controller.docs;

import com.printsys.backend.pojo.Docs;
import com.printsys.backend.service.docs.GetDocsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDocsController {

  @Autowired
  private GetDocsService getDocsService;

  @GetMapping("/docs/get/")
  public List<Docs> get(@RequestParam Map<String, String> data) {
    return getDocsService.getDocs(data);
  }

}
