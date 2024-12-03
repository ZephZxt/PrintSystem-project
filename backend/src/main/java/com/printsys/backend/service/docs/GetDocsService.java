package com.printsys.backend.service.docs;

import com.printsys.backend.pojo.Docs;
import java.util.List;
import java.util.Map;

public interface GetDocsService {
  List<Docs> getDocs(Map<String, String> data);
}
