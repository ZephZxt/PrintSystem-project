package com.printsys.backend.service.impl.checklists;

import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.UpdateListsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateListsServiceImpl implements UpdateListsService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public Map<String, String> updateLists(Map<String, String> data) {
    Map<String, String> map = new HashMap<>();
    int lno = Integer.parseInt(data.get("lno"));
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

    Checklists checklists = checklistsMapper.selectById(lno);
    if(checklists == null) {
      map.put("error_message", "清单不存在或以完成");
      return map;
    }

    Checklists checklists_new = new Checklists(
        checklists.getLno(),
        mName,
        mNum,
        price,
        date,
        false);
    checklistsMapper.updateById(checklists_new);
    map.put("error_message", "success");
    return map;
  }
}
