package com.printsys.backend.service.impl.departments;

import com.printsys.backend.mapper.DepartmentsMapper;
import com.printsys.backend.pojo.Departments;
import com.printsys.backend.service.departments.GetDepsService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDepsServiceImpl implements GetDepsService {

  @Autowired
  private DepartmentsMapper departmentsMapper;

  @Override
  public List<Departments> getDeps() {
    return departmentsMapper.selectList(null);
  }
}
