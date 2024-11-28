package com.printsys.backend.service.impl.docs;

import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.docs.UpdateDocsService;
import com.printsys.backend.utils.UserUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDocsServiceImpl implements UpdateDocsService {

  @Autowired
  private DocsMapper docsMapper;

  @Override
  public Map<String, String> updateDocs(Map<String, String> data) {

    User user = UserUtil.getUser();

    int docs_no = Integer.parseInt(data.get("docs_no"));

    String pName = data.get("pName");
    String bName = data.get("bName");
    String font = data.get("font");
    String mName = data.get("mName");
    int num = Integer.parseInt(data.get("num"));

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

    if(num <= 0) {
      map.put("error_message", "印刷数量需为正数");
      return map;
    }

    Docs docs = docsMapper.selectById(docs_no);
    if(docs == null) {
      map.put("error_message", "单据不存在或已被删除");
      return map;
    }

    if(!Objects.equals(docs.getPNo(), user.getId())) {
      map.put("error_message", "没有权限修改");
      return map;
    }

    Docs new_docs = new Docs(
        docs.getDno(),
        user.getId(),
        pName,
        bName,
        font,
        mName,
        num,
        docs.getCreatetime(),
        new Date(),
        docs.isState()
    );

    docsMapper.updateById(new_docs);
    map.put("error_message", "success");
    return map;
  }
}
