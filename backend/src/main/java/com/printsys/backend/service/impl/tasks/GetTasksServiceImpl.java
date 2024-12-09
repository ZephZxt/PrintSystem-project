package com.printsys.backend.service.impl.tasks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.tasks.GetTasksService;
import com.printsys.backend.utils.UserUtil;
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
    User user = UserUtil.getUser();
    int filter = Integer.parseInt(data.get("filter"));
    if(user.getUsername().equals("Admin") && filter == 0) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 0);
      return tasksMapper.selectList(queryWrapper);
    }
    else if(user.getUsername().equals("Admin") && filter == 1) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 1);
      return tasksMapper.selectList(queryWrapper);
    }
    else if(user.getUsername().equals("Admin") && filter == 2) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("state", 2);
      return tasksMapper.selectList(queryWrapper);
    }
    else if(user.getUsername().equals("Admin") && filter == 3) {
      return tasksMapper.selectList(null);
    }
    else if(filter == 0) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      queryWrapper.eq("state", 0);
      return tasksMapper.selectList(queryWrapper);
    }
    else if(filter == 1) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      queryWrapper.eq("state", 1);
      return tasksMapper.selectList(queryWrapper);
    }
    else if(filter == 2) {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      queryWrapper.eq("state", 2);
      return tasksMapper.selectList(queryWrapper);
    }
    else {
      QueryWrapper<Tasks> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("p_no", user.getId());
      return tasksMapper.selectList(queryWrapper);
    }
  }
}
