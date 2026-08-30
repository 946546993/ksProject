<script setup>
// 我的考核记录列表：场次 / 时间 / 分数 / 等级 / 状态（is_best 标记）
</script>

<template>
  <div class="report-history">
    <el-card>
      <template #header>我的考核记录</template>
      <el-empty description="暂无记录（骨架页面）" />
    </el-card>
  </div>
</template>

<style scoped>
.report-history {
  padding: 32px;
}
</style>
