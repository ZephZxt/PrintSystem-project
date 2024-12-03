package com.printsys.backend.service.impl.tasks;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.mapper.DocsMapper;
import com.printsys.backend.mapper.TasksMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.pojo.Docs;
import com.printsys.backend.pojo.Tasks;
import com.printsys.backend.service.tasks.AddTasksService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTasksServiceImpl implements AddTasksService {

  @Autowired
  private DocsMapper dosMapper;
  @Autowired
  private DepartmentsMapper depMapper;
  @Autowired
  private TasksMapper taskMapper;

  @Override
  public Map<String, String> addTasks(Map<String, String> data) {
    System.out.println(data);

    int dno = Integer.parseInt(data.get("dno"));
    int pd_no = Integer.parseInt(data.get("pd_no"));
    int state = Integer.parseInt(data.get("state"));
    String time = data.get("time");
    Map<String, String> map = new HashMap<>();

    Docs docs = dosMapper.selectById(dno);
    Departments dep = depMapper.selectById(pd_no);
    if(dep == null) {
      map.put("error_message", "部门不存在");
      return map;
    }
    if(docs == null) {
      map.put("error_message", "单据不存在");
      return map;
    }

    docs.setState(true);
    dosMapper.updateById(docs);
    dep.setNum(dep.getNum() + 1);
    depMapper.updateById(dep);
    Tasks task = new Tasks(
        null,
        dno,
        docs.getPNo(),
        docs.getPName(),
        docs.getBName(),
        docs.getNum(),
        docs.getFont(),
        pd_no,
        dep.getPdName(),
        dep.getTeleNumber(),
        time,
        state
    );

    taskMapper.insert(task);
    map.put("error_message", "success");
    return map;
  }

}
