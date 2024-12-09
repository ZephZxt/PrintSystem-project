package com.printsys.backend.service.impl.tasks;

import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.tasks.RemoveTasksService;
import com.printsys.backend.utils.UserUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveTasksServiceImpl implements RemoveTasksService {

  @Autowired
  private TasksMapper tasksMapper;

  @Override
  public Map<String, String> removeTasks(Map<String, String> data) {
    User user = UserUtil.getUser();
    String username = user.getUsername();

    int task_no = Integer.parseInt(data.get("task_no"));

    Tasks tasks = tasksMapper.selectById(task_no);
    Map<String, String> map = new HashMap<>();

    if(tasks == null) {
      map.put("error_message", "任务不存在或已被删除");
      return map;
    }

    if (!tasks.getPNo().equals(user.getId()) && !username.equals("Admin")) {
      map.put("error_message", "没有权限删除");
      return map;
    }

    tasksMapper.deleteById(task_no);
    map.put("error_message", "success");
    return map;
  }

}
