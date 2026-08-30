import { defineStore } from 'pinia'

/**
 * 用户状态：登录态 + 个人信息（补录标记 profileFilled 决定是否强制跳补录页）
 */
export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: null
  }),
  getters: {
    isLoggedIn: (state) => !!state.token,
    profileFilled: (state) => state.userInfo?.profileFilled === 1
  },
  actions: {
    setToken(token) {
      this.token = token
      localStorage.setItem('token', token)
    },
    setUserInfo(info) {
      this.userInfo = info
    },
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
    }
  }
})
