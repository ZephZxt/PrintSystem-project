package com.printsys.backend.service.impl.checklists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.GetListsService;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GetListsServiceImpl implements GetListsService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public List<Checklists> getLists(Map<String, String> data) {
    int type = Integer.parseInt(data.get("filter"));

    if(type == 2) {
      return checklistsMapper.selectList(null);
    }
    else if(type == 1) {
      QueryWrapper<Checklists> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 1);
      return checklistsMapper.selectList(queryWrapper);
    }
    else {
      QueryWrapper<Checklists> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 0);
      return checklistsMapper.selectList(queryWrapper);
    }

  }
}
