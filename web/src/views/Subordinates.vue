<script setup>
// 主管视图：直接下属列表（首期不做多层展开）
</script>

<template>
  <div class="subordinates">
    <el-card>
      <template #header>我的下属</template>
      <el-empty description="暂无下属（骨架页面）" />
    </el-card>
  </div>
</template>

<style scoped>
.subordinates {
  padding: 32px;
}
</style>
