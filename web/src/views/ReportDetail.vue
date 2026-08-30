<script setup>
// 能力画像报告页 [SDD §10.3]
// 组件规划：ScoreHeader（综合分+等级徽章+标定期提示）
//         RadarChart（ECharts 六维雷达）
//         WeakPointList（薄弱点卡片）
//         AdviceList（学习建议，异步润色先展示模板版）
//         HistoryDrawer（本场答题明细抽屉）
</script>

<template>
  <div class="report-page">
    <el-card>
      <template #header>能力画像报告</template>
      <el-empty description="报告加载中…（骨架页面）" />
    </el-card>
  </div>
</template>

<style scoped>
.report-page {
  padding: 32px;
}
</style>
