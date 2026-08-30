<script setup>
// 考试首页：场次信息、规则说明、开始按钮、剩余次数
// TODO: 拉场次列表 → 校验窗口期 → 开始（POST /exam/start）→ 跳 /exam/doing
</script>

<template>
  <div class="exam-home">
    <el-card>
      <template #header>AI 能力测评</template>
      <el-empty description="暂无进行中的场次（骨架页面）" />
    </el-card>
  </div>
</template>

<style scoped>
.exam-home {
  padding: 32px;
}
</style>
