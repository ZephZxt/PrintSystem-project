package com.printsys.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
  @TableId(type = IdType.AUTO)
  private Integer tno;
  private Integer dno;
  private Integer pNo;
  private String pName;
  private String bName;
  private Integer num;
  private String font;
  private Integer pdNo;
  private String pdName;
  private String teleNumber;
  private String time;
  private Integer state;


}
