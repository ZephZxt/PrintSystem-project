package com.printsys.backend.service.impl.checklists;

import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.UpdateListStateService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

@Service
public class UpdateListStateServiceImpl implements UpdateListStateService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public Map<String, String> updateListState(Map<String, String> data) {
    String state = data.get("state");
    int list_no = Integer.parseInt(data.get("lno"));
    Checklists checklists = checklistsMapper.selectById(list_no);
    Map<String, String> map = new HashMap<>();

    if(checklists == null) {
      map.put("error_message", "清单不存在或已被删除");
      return map;
    }

    if (state.equals("1")) {
      checklists.setState(true);
      checklistsMapper.updateById(checklists);
      map.put("error_message", "success");
      return map;
    }
    else if(state.equals("0")) {
      checklists.setState(false);
      checklistsMapper.updateById(checklists);
      map.put("error_message", "success");
      return map;
    }
    else {
      map.put("error_message", "参数错误");
      return map;
    }
  }
}
