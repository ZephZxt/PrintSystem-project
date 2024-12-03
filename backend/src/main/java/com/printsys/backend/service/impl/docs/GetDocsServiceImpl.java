package com.printsys.backend.service.impl.docs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.GetDocsService;
import com.printsys.backend.utils.UserUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDocsServiceImpl implements GetDocsService {

  @Autowired
  private DocsMapper docsMapper;

  @Override
  public List<Docs> getDocs(Map<String, String> data) {
    User user = UserUtil.getUser();
    int type = Integer.parseInt(data.get("filter"));

    if(Objects.equals(user.getUsername(), "Admin") && type == 0) {
      return docsMapper.selectList(null);
    }
    else if(Objects.equals(user.getUsername(), "Admin") && type == 1) {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 1);
      return docsMapper.selectList(queryWrapper);
    }
    else if(Objects.equals(user.getUsername(), "Admin") && type == 2) {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 0);
      return docsMapper.selectList(queryWrapper);
    }
    else if(type == 0) {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      queryWrapper.eq("state", 0);
      return docsMapper.selectList(queryWrapper);
    }
    else if(type == 1) {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      queryWrapper.eq("state", 1);
      return docsMapper.selectList(queryWrapper);
    }
    else {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());

      return docsMapper.selectList(queryWrapper);
    }



  }
}
