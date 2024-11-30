package com.printsys.backend.service.impl.departments;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.service.departments.RemoveDepsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveDepsImpl implements RemoveDepsService {

  @Autowired
  private DepartmentsMapper departmentsMapper;

  @Override
  public Map<String, String> removeDeps(Map<String, String> data) {

    Map<String, String> map = new HashMap<>();

    int dep_no = Integer.parseInt(data.get("dep_no"));

    Departments departments = departmentsMapper.selectById(dep_no);

    if(departments == null){
      map.put("error_message", "部门不存在或已被删除");
      return map;
    }

    departmentsMapper.deleteById(dep_no);
    map.put("error_message", "success");
    return map;

  }
}
