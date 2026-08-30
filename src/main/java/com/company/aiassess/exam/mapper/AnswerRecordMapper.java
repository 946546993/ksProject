package com.company.aiassess.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.aiassess.exam.entity.AnswerRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerRecordMapper extends BaseMapper<AnswerRecord> {

    // TODO: selectDirtyQuestionIds —— 按回流标记聚合出待回流题目（LIMIT 200/批）
    // TODO: selectByQuestionAndReflowFlag —— 回流任务取未参与回流的流水
}
