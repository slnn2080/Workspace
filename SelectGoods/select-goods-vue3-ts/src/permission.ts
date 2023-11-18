/*
  路由鉴权 相关: 项目当中的路由能不能被访问的权限的设置
    - 某一个路由什么条件下 可以被访问
    - 某一个路由什么条件下 不可以被访问
*/

// 引入 路由器 router
import router from './router'

// 1. 引入 进度条 插件
import nprogress from 'nprogress'
// 2. 引入 进度条 样式
import 'nprogress/nprogress.css'
nprogress.configure({ showSpinner: false })

import settings from './settings'

// 路由鉴权: 判断用户是否登录 需要判断 store 中是否有token
// 1. 获取 store
import useUserStore from './store/userStore'

// 全局前置守卫: 访问某一条路由前 执行的函数
router.beforeEach(async (to, _, next) => {
  // 进度条业务: 访问路由前 进度条开始动
  nprogress.start()

  // 在回调内部调用 useUserStore 因为同步调用会报错
  const userStore = useUserStore()

  // 判断 用户的登录 状态
  const token = userStore.token
  // 获取 username, 判断进入其它组件之前 是否有用户信息, 如果有再放行 没有则先请求
  const username = userStore.username

  if (token) {
    // 用户已登录 的情况
    // 登录成功后不能再访问 login, 指向首页
    if (to.path === '/login') {
      return next({ path: '/' })
    } else {
      // 登录成功访问其余的6个一级路由, 不含login

      // 为了确保在访问其它的路由之前, store中是有 用户信息(用户名 头像) 的所以我们要添加如下的判断
      // next()
      // 1. 有 用户信息(username) 则放行
      if (username) {
        return next()
      } else {
        // 2. 没有 用户信息 发起请求 获取到了用户信息在放行
        try {
          await userStore.getUserInfo()
          // 获取用户信息后我们再放行
          // 万一刷新页面的时候 是异步路由 有可能我们获取到了用户信息但是异步路由还没有加载完毕 就会出现白屏 我们需要确保我们获取到了用户信息 并确保异步路由加载完毕再放行
          // return next()
          // 这种写法就能确保加载完后我们再放行
          return next({ ...to })
        } catch (err) {
          /*
            没有用户信息 我们发起了请求 但是还没有拿到用户数据, 这里的情况就是token过期了, 只有token过期了 发请求才会拿不到数据
             (或者用户手动修改了本地存储中的token)

            用户过期了需要让用户回到login页面重新登录 获取新的token, 也就是清空用户信息 和 token 重新执行login, 这里也就是我们封装在store中的退出登录动能
          */
          await userStore.userLogout()
          return next({ path: '/login' })
        }
      }
    }
  } else {
    // 用户未登录 的情况
    // 未登录状态下 只有访问 login 的时候 放行, 其余的路由都不放行, 让其指回login, 然后将用户想访问 而 没有访问成功的页面(?redirect=/acl/user)通过 query参数 传递给 /login 接口
    if (to.path === '/login') {
      return next()
    } else {
      // 将用户想访问 而 没有访问成功的页面 带给login, 这样用户登录成功后 可以直接跳过去
      return next({ path: '/login', query: { redirect: to.path } })
    }
  }
})

// 全局后置守卫: 访问路由后 执行的函数, 想想一个页面刷新后的时间点, 并不是要离开该路由
router.afterEach((to) => {
  // 进度条业务: 访问路由后 进度条消失
  nprogress.done()

  // 修改页签名
  document.title = `${settings.title} - ${to.meta.title}`
})
