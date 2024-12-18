package com.printsys.backend.service.impl.docs;

import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.RemoveDocsService;
import com.printsys.backend.utils.UserUtil;
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

    String username = user.getUsername();

    int doc_no = Integer.parseInt(data.get("doc_no"));

    Docs docs = docsMapper.selectById(doc_no);

    Map<String, String> map = new HashMap<>();

    if(docs == null ) {
      map.put("error_message", "单据不存在或已被删除");
      return map;
    }

    if(!Objects.equals(docs.getPNo(), user.getId()) && !Objects.equals(username,"Admin")) {
      map.put("error_message", "没有权限删除");
      return map;
    }


    docsMapper.deleteById(doc_no);
    map.put("error_message", "success");

    return map;


  }
}
