package com.printsys.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Docs {
  @TableId(type = IdType.AUTO)
  private Integer dno;
  private Integer pNo;
  private String pName;
  private String bName;
  private String font;
  private String mName;
  private Integer num;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createtime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifytime;
  private boolean state;
}
