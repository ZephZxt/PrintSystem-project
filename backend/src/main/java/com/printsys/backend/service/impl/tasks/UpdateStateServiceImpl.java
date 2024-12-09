package com.printsys.backend.service.impl.tasks;

import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.tasks.UpdateStateService;
import com.printsys.backend.utils.UserUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStateServiceImpl implements UpdateStateService {

  @Autowired
  private TasksMapper tasksMapper;

  @Override
  public Map<String, String> updateState(Map<String, String> data) {

    User user = UserUtil.getUser();
    int state = Integer.parseInt(data.get("state"));
    int task_no = Integer.parseInt(data.get("task_no"));
    Tasks tasks = tasksMapper.selectById(task_no);
    Map<String, String> map = new HashMap<>();

    if(tasks == null) {
      map.put("error_message", "任务不存在或已被删除");
      return map;
    }

    if(!tasks.getPNo().equals(user.getId()) && !user.getUsername().equals("Admin")) {
      map.put("error_message", "没有权限修改");
      return map;
    }

    tasks.setState(state);
    tasksMapper.updateById(tasks);
    map.put("error_message", "success");
    return map;
  }

}
