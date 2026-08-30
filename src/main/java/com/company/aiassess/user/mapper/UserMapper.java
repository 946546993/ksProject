package com.company.aiassess.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.aiassess.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
