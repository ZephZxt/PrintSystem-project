package com.printsys.backend.service.impl.departments;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.service.departments.AddDepsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddDepsServiceImpl implements AddDepsService {

  @Autowired
  private DepartmentsMapper departmentsMapper;

  @Override
  public Map<String, String> addDeps(Map<String, String> data) {

    Map<String, String> map = new HashMap<>();
    String pdName = data.get("pdName");
    String address = data.get("address");
    String teleNumber = data.get("teleNumber");

    if(pdName == null || pdName.isEmpty()) {
      map.put("error_message", "印刷部门名称不能为空");
      return map;
    }

    if(pdName.length() > 50) {
      map.put("error_message", "印刷部门名称过长");
      return map;
    }

    if(address == null || address.isEmpty()) {
      map.put("error_message", "印刷部门地址不能为空");
      return map;
    }

    if(address.length() > 50) {
      map.put("error_message", "印刷部门地址过长");
      return map;
    }

    if(teleNumber == null || teleNumber.isEmpty()) {
      map.put("error_message", "印刷部门电话不能为空");
      return map;
    }

    if(teleNumber.length() > 50) {
      map.put("error_message", "印刷部门电话过长");
      return map;
    }

    Departments departments = new Departments(null, pdName, address, teleNumber,0);

    departmentsMapper.insert(departments);
    map.put("error_message", "success");


    return map;
  }
}
