import { createRouter, createWebHistory } from 'vue-router'

// 路由清单 [SDD §10.1]
const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/onboarding',
    name: 'Onboarding',
    component: () => import('../views/Onboarding.vue'),
    meta: { title: '首次补录', requiresAuth: true }
  },
  {
    path: '/exam',
    name: 'ExamHome',
    component: () => import('../views/ExamHome.vue'),
    meta: { title: '考试首页', requiresAuth: true }
  },
  {
    path: '/exam/doing',
    name: 'ExamDoing',
    component: () => import('../views/ExamDoing.vue'),
    meta: { title: '答题', requiresAuth: true }
  },
  {
    path: '/report/history',
    name: 'ReportHistory',
    component: () => import('../views/ReportHistory.vue'),
    meta: { title: '我的考核记录', requiresAuth: true }
  },
  {
    path: '/report/:recordId',
    name: 'ReportDetail',
    component: () => import('../views/ReportDetail.vue'),
    meta: { title: '能力画像报告', requiresAuth: true }
  },
  {
    path: '/subordinates',
    name: 'Subordinates',
    component: () => import('../views/Subordinates.vue'),
    meta: { title: '下属列表', requiresAuth: true }
  },
  {
    path: '/subordinates/:userId',
    name: 'SubordinateDetail',
    component: () => import('../views/SubordinateDetail.vue'),
    meta: { title: '下属画像', requiresAuth: true }
  },
  {
    path: '/admin',
    component: () => import('../views/admin/AdminLayout.vue'),
    meta: { requiresAuth: true },
    children: [
      { path: '', redirect: '/admin/dashboard' },
      { path: 'dashboard', name: 'AdminDashboard', component: () => import('../views/admin/Dashboard.vue'), meta: { title: '看板' } },
      { path: 'sessions', name: 'AdminSessions', component: () => import('../views/admin/Sessions.vue'), meta: { title: '场次管理' } },
      { path: 'questions', name: 'AdminQuestions', component: () => import('../views/admin/Questions.vue'), meta: { title: '题库管理' } },
      { path: 'review', name: 'AdminReview', component: () => import('../views/admin/Review.vue'), meta: { title: '举报复核' } },
      { path: 'config', name: 'AdminConfig', component: () => import('../views/admin/Config.vue'), meta: { title: '参数配置' } }
    ]
  },
  { path: '/', redirect: '/exam' },
  { path: '/:pathMatch(.*)*', redirect: '/exam' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 登录态守卫：TODO 待 JWT 接入后按 token 判断，未登录跳 /login
router.beforeEach((to) => {
  document.title = to.meta.title ? `${to.meta.title} - AI 能力测评` : 'AI 能力测评'
  return true
})

export default router
