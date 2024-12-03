package com.printsys.backend.service.tasks;

import java.util.Map;

public interface UpdateTasksService {
  Map<String, String> updateTasks(Map<String, String> data);
}
