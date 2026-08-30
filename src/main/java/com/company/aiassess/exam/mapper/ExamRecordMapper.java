package com.company.aiassess.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.aiassess.exam.entity.ExamRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {

    // TODO: 超时结算扫描 —— WHERE status=1 AND deadline < NOW()，走 idx_deadline 索引
}
