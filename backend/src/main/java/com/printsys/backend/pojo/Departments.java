package com.printsys.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departments {
  @TableId(type = IdType.AUTO)
  private Integer pdNo;
  private String pdName;
  private String address;
  private String teleNumber;

}
