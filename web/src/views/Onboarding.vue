<script setup>
// 首次补录页：岗位类别 / 职级 / AI经验自评 / 每周使用频次 4 字段
// 强制完成才能开考（未补录后端返回 41001）
import { reactive } from 'vue'

const form = reactive({
  jobCategory: null,
  jobLevel: '',
  aiExpSelf: null,
  aiUsageFreq: null
})

// TODO: 提交 /api/user/profile → 成功后跳 /exam
const onSubmit = () => {}
</script>

<template>
  <div class="onboarding-page">
    <el-card>
      <template #header>补录个人信息（首次使用需完成）</template>
      <el-form :model="form" label-width="120px" style="max-width: 480px">
        <el-form-item label="岗位类别" required>
          <el-select v-model="form.jobCategory" placeholder="请选择">
            <el-option label="技术" :value="1" />
            <el-option label="产品" :value="2" />
            <el-option label="销售" :value="3" />
            <el-option label="职能" :value="4" />
            <el-option label="其他" :value="5" />
          </el-select>
        </el-form-item>
        <el-form-item label="职级" required>
          <el-input v-model="form.jobLevel" placeholder="如 P5 / T3" />
        </el-form-item>
        <el-form-item label="AI 经验自评" required>
          <el-radio-group v-model="form.aiExpSelf">
            <el-radio :value="1">几乎没用过</el-radio>
            <el-radio :value="2">偶尔</el-radio>
            <el-radio :value="3">常用</el-radio>
            <el-radio :value="4">深度使用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="每周使用频次" required>
          <el-radio-group v-model="form.aiUsageFreq">
            <el-radio :value="0">零次</el-radio>
            <el-radio :value="1">低（1-3 次）</el-radio>
            <el-radio :value="2">中（4-10 次）</el-radio>
            <el-radio :value="3">高（>10 次）</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.onboarding-page {
  padding: 32px;
  display: flex;
  justify-content: center;
}
</style>
