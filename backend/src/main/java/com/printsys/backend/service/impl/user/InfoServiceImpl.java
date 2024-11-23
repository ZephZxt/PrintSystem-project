package com.printsys.backend.service.impl.user;

import com.printsys.backend.pojo.User;
import com.printsys.backend.service.impl.utils.UserDetailsImpl;
import com.printsys.backend.service.user.InfoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

  @Override
  public Map<String, String> getInfo() {
    UsernamePasswordAuthenticationToken authenticationToken =
        (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
    User user = loginUser.getUser();
    Map<String, String> map = new HashMap<>();
    map.put("error_message", "success");
    map.put("id", user.getId().toString());
    map.put("username", user.getUsername());
    map.put("role", user.getRole());
    return map;
  }
}
