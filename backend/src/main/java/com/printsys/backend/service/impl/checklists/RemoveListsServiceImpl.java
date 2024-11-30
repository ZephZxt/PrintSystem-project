package com.printsys.backend.service.impl.checklists;

import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.RemoveListsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveListsServiceImpl implements RemoveListsService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public Map<String, String> removeLists(Map<String, String> data) {
    Map<String, String> map = new HashMap<>();

    int lno = Integer.parseInt(data.get("lno"));

    Checklists checklists = checklistsMapper.selectById(lno);
    if (checklists == null) {
      map.put("error_message", "清单不存在或已完成");
      return map;
    }

    checklistsMapper.deleteById(lno);
    map.put("error_message", "success");
    return map;
  }
}
