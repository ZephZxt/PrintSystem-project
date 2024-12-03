package com.printsys.backend.service.checklists;

import com.printsys.backend.pojo.Checklists;
import java.util.List;
import java.util.Map;

public interface GetListsService {
  List<Checklists> getLists(Map<String, String> data);

}
