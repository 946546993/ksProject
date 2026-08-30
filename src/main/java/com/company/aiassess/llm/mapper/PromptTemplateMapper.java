package com.company.aiassess.llm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.aiassess.llm.entity.PromptTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromptTemplateMapper extends BaseMapper<PromptTemplate> {
}
