package com.printsys.backend.service.impl.tasks;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.pojo.User;
import com.printsys.backend.service.tasks.UpdateTasksService;
import com.printsys.backend.utils.UserUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTasksServiceImpl implements UpdateTasksService {

  @Autowired
  private TasksMapper tasksMapper;
  @Autowired
  private DepartmentsMapper departmentsMapper;

  @Override
  public Map<String, String> updateTasks(Map<String, String> data) {

    User user = UserUtil.getUser();
    String username = user.getUsername();

    Map<String, String> map = new HashMap<>();

    int task_no = Integer.parseInt(data.get("task_no"));

    String pd_no = data.get("pd_no");
    String time = data.get("time");

    Tasks tasks = tasksMapper.selectById(task_no);

    if (tasks == null) {
      map.put("error_message", "任务不存在或已被删除");
      return map;
    }

    if (pd_no == null || pd_no.isEmpty()) {
      map.put("error_message", "印刷部门编号不能为空");
      return map;
    }

    if (time == null || time.isEmpty()) {
      map.put("error_message", "时间不能为空");
      return map;
    }

    if (!Objects.equals(tasks.getPNo(), user.getId()) && !Objects.equals(username, "Admin")) {
      map.put("error_message", "没有权限修改");
      return map;
    }

    Departments departments_new = departmentsMapper.selectById(Integer.parseInt(pd_no));
    Departments departments_old = departmentsMapper.selectById(tasks.getPdNo());

    if (departments_new == null) {
      map.put("error_message", "印刷部门不存在");
      return map;
    }

    String pName_new = departments_new.getPdName();
    String teleNumber_new = departments_new.getTeleNumber();

    tasks.setPdNo(Integer.parseInt(pd_no));
    tasks.setTime(time);
    tasks.setPdName(pName_new);
    tasks.setTeleNumber(teleNumber_new);

    departments_new.setNum(departments_new.getNum() + 1);
    departmentsMapper.updateById(departments_new);
    departments_old.setNum(departments_old.getNum() - 1);
    departmentsMapper.updateById(departments_old);

    tasksMapper.updateById(tasks);
    map.put("error_message", "success");
    return map;

  }
}
