package com.printsys.backend.controller.docs;

import com.printsys.backend.service.docs.RemoveDocsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveDocsController {

  @Autowired
  private RemoveDocsService removeDocsService;

  @PostMapping("/docs/remove/")
  public Map<String, String> remove(@RequestParam Map<String, String> data) {
    return removeDocsService.removeDocs(data);
  }
}
