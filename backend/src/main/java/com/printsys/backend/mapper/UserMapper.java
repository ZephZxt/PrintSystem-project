package com.printsys.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.printsys.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
