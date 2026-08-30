<script setup>
// 免登回调页：企微/钉钉 OAuth 跳转落点 /login?code=xxx
// TODO: 取 code 调 /api/auth/login 换 JWT → 未补录跳 /onboarding，已补录跳 /exam
</script>

<template>
  <div class="login-page">
    <el-card class="login-card">
      <h2>AI 能力测评</h2>
      <p>正在登录…</p>
    </el-card>
  </div>
</template>

<style scoped>
.login-page {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login-card {
  text-align: center;
  padding: 24px;
}
</style>
