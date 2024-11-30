package com.printsys.backend.service.impl.docs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.GetDocsService;
import com.printsys.backend.utils.UserUtil;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDocsServiceImpl implements GetDocsService {

  @Autowired
  private DocsMapper docsMapper;

  @Override
  public List<Docs> getDocs() {
    User user = UserUtil.getUser();

    if(Objects.equals(user.getUsername(), "Admin")) {
      return docsMapper.selectList(null);
    }
    else {
      QueryWrapper<Docs> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      return docsMapper.selectList(queryWrapper);
    }

  }
}
