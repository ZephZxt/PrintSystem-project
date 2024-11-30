package com.printsys.backend.service.impl.departments;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.service.departments.UpdateDepsService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDepsServiceImpl implements UpdateDepsService {

  @Autowired
  private DepartmentsMapper departmentsMapper;

  @Override
  public Map<String, String> updateDeps(Map<String, String> data) {

    Map<String, String> map = new HashMap<>();
    int dep_no = Integer.parseInt(data.get("dep_no"));

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

    Departments departments = departmentsMapper.selectById(dep_no);
    if( departments == null ) {
      map.put("error_message", "部门不存在或已被删除");
      return map;
    }

    Departments new_dep = new Departments(
        departments.getPdNo(),
        pdName,
        address,
        teleNumber
    );

    departmentsMapper.updateById(new_dep);
    map.put("error_message", "success");
    return map;
  }
}
