package com.company.aiassess.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.aiassess.question.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    // TODO: matchQuestions —— 选题核心 SQL，见 SDD §4.2.1
    //  WHERE deleted=0 AND status=1 AND dimension=#{dim}
    //    AND difficulty_b BETWEEN #{bTarget}-0.5 AND #{bTarget}+0.5   ← BETWEEN 写法才走 idx_dim_diff 范围扫描
    //    AND job_fit IN (#{jobCategory},'ALL')
    //    AND id NOT IN (已答题) AND knowledge_point NOT IN (已考察知识点) LIMIT 20
    List<Question> matchQuestions(@Param("dim") String dim,
                                  @Param("bTarget") double bTarget,
                                  @Param("jobCategory") String jobCategory,
                                  @Param("excludedQids") List<Long> excludedQids,
                                  @Param("excludedPoints") List<String> excludedPoints);
}
