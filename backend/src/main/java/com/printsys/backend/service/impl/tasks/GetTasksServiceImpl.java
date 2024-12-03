package com.printsys.backend.service.impl.tasks;

import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.service.tasks.GetTasksService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTasksServiceImpl implements GetTasksService {

  @Autowired
  private TasksMapper tasksMapper;

  @Override
  public List<Tasks> getTasks(Map<String, String> data) {
    return tasksMapper.selectList(null);
  }
}
