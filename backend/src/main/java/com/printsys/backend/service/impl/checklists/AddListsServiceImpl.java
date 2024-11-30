package com.printsys.backend.service.impl.checklists;

import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.AddListsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddListsServiceImpl implements AddListsService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public Map<String, String> addLists(Map<String, String> data) {
    Map<String, String> map = new HashMap<>();

    String mName = data.get("mName");
    String mNum = data.get("mNum");
    String price = data.get("price");
    String date = data.get("date");

    if(mName == null || mName.isEmpty()) {
      map.put("error_message", "采购材料名称不能为空");
      return map;
    }

    if(mName.length() > 200) {
      map.put("error_message", "数据错误");
      return map;
    }

    if(mNum == null || mNum.isEmpty()) {
      map.put("error_message", "采购材料数量不能为空");
      return map;
    }

    if(mNum.length() > 200) {
      map.put("error_message", "数据错误");
      return map;
    }

    if(price == null || price.isEmpty()) {
      map.put("error_message", "采购材料价格不能为空");
      return map;
    }

    if(price.length() > 200) {
      map.put("error_message", "数据错误");
      return map;
    }

    if(date.length() > 50) {
      map.put("error_message", "数据错误");
    }

    Checklists checklists = new Checklists(null, mName, mNum, price, date, false);
    checklistsMapper.insert(checklists);
    map.put("error_message", "success");
    return map;
  }
}
