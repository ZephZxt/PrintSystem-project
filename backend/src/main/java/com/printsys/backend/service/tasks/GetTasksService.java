package com.printsys.backend.service.tasks;

import com.printsys.backend.pojo.Tasks;
import java.util.List;
import java.util.Map;

public interface GetTasksService {
  List<Tasks> getTasks(Map<String, String> data);
}
