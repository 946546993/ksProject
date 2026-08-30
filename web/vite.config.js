import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      // 后端接口统一走 /api，本地开发代理到 Spring Boot 8080
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
