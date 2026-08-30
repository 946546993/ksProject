<script setup>
// 管理后台布局：侧边菜单 + 内容区
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<template>
  <el-container class="admin-layout">
    <el-aside width="200px">
      <el-menu router :default-active="route.path" class="admin-menu">
        <el-menu-item index="/admin/dashboard">看板</el-menu-item>
        <el-menu-item index="/admin/sessions">场次管理</el-menu-item>
        <el-menu-item index="/admin/questions">题库管理</el-menu-item>
        <el-menu-item index="/admin/review">举报复核</el-menu-item>
        <el-menu-item index="/admin/config">参数配置</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<style scoped>
.admin-layout {
  height: 100%;
}
.admin-menu {
  height: 100%;
}
</style>
