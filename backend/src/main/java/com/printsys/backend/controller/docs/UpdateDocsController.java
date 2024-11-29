package com.printsys.backend.controller.docs;

import com.printsys.backend.service.docs.UpdateDocsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateDocsController {

  @Autowired
  private UpdateDocsService updateDocsService;

  @PostMapping("/docs/update/")
  public Map<String, String> update(@RequestParam Map<String, String> data) {

    return updateDocsService.updateDocs(data);
  }

}
