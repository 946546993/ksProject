import axios from 'axios'
import { ElMessage } from 'element-plus'

/**
 * axios 封装：统一响应体 { code, message, data, traceId }
 * code=0 成功；非 0 按错误码表映射用户可见文案 [SDD §6.1]
 */
const request = axios.create({
  baseURL: '/api',
  timeout: 15000
})

request.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

request.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code === 0) {
      return res.data
    }
    // TODO: 按错误码映射跳转（40101 跳免登 / 41001 跳补录 / 41004 跳报告页等）
    ElMessage.error(res.message || '请求失败')
    return Promise.reject(res)
  },
  (error) => {
    ElMessage.error(error.message || '网络异常')
    return Promise.reject(error)
  }
)

export default request
