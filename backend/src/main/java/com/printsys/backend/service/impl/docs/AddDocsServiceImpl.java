package com.printsys.backend.service.impl.docs;

import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.AddDocsService;
import com.printsys.backend.utils.UserUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddDocsServiceImpl implements AddDocsService {

  @Autowired
  private DocsMapper docsMapper;

  @Override
  public Map<String, String> addDocs(Map<String, String> data) {
    User user = UserUtil.getUser();

    String pName = data.get("pName");
    String bName = data.get("bName");
    String font = data.get("font");
    String mName = data.get("mName");
    String num_s = data.get("num");
    int num = 0;

    Map<String, String> map = new HashMap<>();

    if(pName == null || pName.isEmpty()) {
      map.put("error_message", "出版商名称不能为空");
      return map;
    }

    if(pName.length() > 50) {
      map.put("error_message", "名称过长");
      return map;
    }

    if(bName == null || bName.isEmpty()) {
      map.put("error_message", "书籍名称不能为空");
      return map;
    }

    if(bName.length() > 50) {
      map.put("error_message", "名称过长");
      return map;
    }

    if(font == null || font.isEmpty()) {
      map.put("error_message", "字体类型不能为空");
      return map;
    }

    if(font.length() > 50) {
      map.put("error_message", "字体类型过长");
      return map;
    }

    if(mName == null || mName.isEmpty()) {
      map.put("error_message", "印刷材料名称不能为空");
      return map;
    }

    if(mName.length() > 100) {
      map.put("error_message", "印刷材料名称过长");
      return map;
    }

    if(num_s == null || num_s.isEmpty()) {
      map.put("error_message", "印刷数量不能为空");
      return map;
    }
    else {
       num = Integer.parseInt(data.get("num"));
    }


    if(num <= 0) {
      map.put("error_message", "印刷数量需为正数");
      return map;
    }
    Date now = new Date();
    Docs docs = new Docs(null, user.getId(), pName, bName, font, mName, num, now, now, false);

    docsMapper.insert(docs);
    map.put("error_message", "success");

    return map;
  }
}
