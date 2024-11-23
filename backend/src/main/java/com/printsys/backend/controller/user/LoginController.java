package com.printsys.backend.controller.user;

import com.printsys.backend.service.user.LoginService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @Autowired
  private LoginService loginService;
  @PostMapping("/user/token/")
  public Map<String, String> getToken(@RequestParam Map<String, String> map){
    String username = map.get("username");
    String password = map.get("password");
    return loginService.getToken(username, password);

  }

}
