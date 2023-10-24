import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import path from 'path'

// 获取环境变量
export default defineConfig(({ command, mode }) => {
  // 使用 loadEnv 获取环境对象
  // 参数1: 告诉loadEnv加载哪个环境变量的文件, 传入mode 默认是开发环境
  // 参数2: 传入项目根目录 用于告诉loadEnv环境变量的文件在哪 传入 process.cwd()
  const env = loadEnv(mode, process.cwd())
  /*
  {
    VITE_APP_TITLE: '硅谷甄选运营平台',
    VITE_APP_BASE_API: '/api',
    VITE_SERVER: 'http://sph-api.atguigu.cn',
    VITE_USER_NODE_ENV: 'development'
  }
  */
  return {
    plugins: [
      vue(),
      createSvgIconsPlugin({
        // 将来我们的svg图标需要放在 icons 文件夹中
        iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
        symbolId: 'icon-[dir]-[name]'
      })
    ],
    resolve: {
      alias: {
        '@': path.resolve('./src') // 相对路径别名配置，使用 @ 代替 src
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
          additionalData: '@use "./src/styles/variable.scss" as *;'
        }
      }
    },
    server: {
      proxy: {
        [env.VITE_APP_BASE_API]: {
          target: env.VITE_SERVER,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    }
  }
})
