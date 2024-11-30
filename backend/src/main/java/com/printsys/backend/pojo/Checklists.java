package com.printsys.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checklists {
  @TableId(type = IdType.AUTO)
  private Integer lno;
  private String mName;
  private String mNum;
  private String price;
  private String date;
  private boolean state;

}
