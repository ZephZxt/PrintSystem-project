package com.printsys.backend.service.impl.docs;

import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.RemoveDocsService;
import com.printsys.backend.utils.UserUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveDocsServiceImpl implements RemoveDocsService {

  @Autowired
  private DocsMapper docsMapper;

  @Override
  public Map<String, String> removeDocs(Map<String, String> data) {

    User user = UserUtil.getUser();

    int docs_no = Integer.parseInt(data.get("docs_no"));

    Docs docs = docsMapper.selectById(docs_no);

    Map<String, String> map = new HashMap<String, String>();

    if(docs == null ) {
      map.put("error_message", "单据不存在或已被删除");
      return map;
    }

    if(!Objects.equals(docs.getPNo(), user.getId())) {
      map.put("error_message", "没有权限删除");
      return map;
    }


    docsMapper.deleteById(docs_no);
    map.put("error_message", "success");

    return map;


  }
}
