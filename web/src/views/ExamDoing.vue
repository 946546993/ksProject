<script setup>
// 答题页（核心页面）[SDD §10.2]
// 交互规则：
// 1. 倒计时以服务端 remainingSeconds 为基准，每次答题响应刷新；剩余 60s 变色；到 0 自动 finish
// 2. 提交后按钮立即置灰防双击
// 3. 解析区展示后"下一题"才可点，无返回上一题的任何入口
// 4. 生成等待超 1s 显示骨架屏；收到 41007 间隔 2s 重试，最多 3 次
// 5. 断点续答：进入时先调 GET /exam/resume
import { ref } from 'vue'

const remainingSeconds = ref(0)
const progress = ref('0/25')
const question = ref(null) // TODO: NextQuestionVO（无答案/解析）
</script>

<template>
  <div class="exam-doing">
    <!-- 顶栏：标题 + 倒计时 + 进度 -->
    <div class="topbar">
      <span class="title">AI 能力测评</span>
      <span class="timer">⏱ {{ remainingSeconds }}</span>
      <span class="progress">第 {{ progress }} 题</span>
    </div>

    <!-- 题目区 -->
    <el-card class="question-card">
      <el-skeleton v-if="!question" :rows="6" animated />
      <template v-else>
        <!-- TODO: 题干 + 选项（单选 radio / 多选 checkbox） -->
        <el-alert type="warning" :closable="false" title="此题提交后不可修改" />
        <div class="actions">
          <el-button text>有问题？标记此题</el-button>
          <el-button type="primary">提交本题</el-button>
        </div>
      </template>
    </el-card>

    <!-- 解析区：提交后展示（正确答案 + 解析 + 下一题） -->
    <el-card v-if="false" class="explain-card">
      <p>✅ 正确答案：</p>
      <p>📖 解析：</p>
      <el-button type="primary">下一题 →</el-button>
    </el-card>
  </div>
</template>

<style scoped>
.exam-doing {
  max-width: 860px;
  margin: 0 auto;
  padding: 24px;
}
.topbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  margin-bottom: 16px;
  background: #f5f7fa;
  border-radius: 8px;
}
.actions {
  margin-top: 16px;
  display: flex;
  justify-content: space-between;
}
.explain-card {
  margin-top: 16px;
}
</style>
