package com.printsys.backend.service.impl.checklists;

import com.printsys.backend.mapper.ChecklistsMapper;
import com.printsys.backend.pojo.Checklists;
import com.printsys.backend.service.checklists.GetListsService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetListsServiceImpl implements GetListsService {

  @Autowired
  private ChecklistsMapper checklistsMapper;

  @Override
  public List<Checklists> getLists() {
    return checklistsMapper.selectList(null);
  }
}
