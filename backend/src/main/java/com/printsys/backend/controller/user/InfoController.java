package com.printsys.backend.controller.user;

import com.printsys.backend.service.user.InfoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
  @Autowired
  private InfoService infoService;

  @GetMapping("/user/info/")
  public Map<String, String> getInfo() {
    return infoService.getInfo();
  }
}
