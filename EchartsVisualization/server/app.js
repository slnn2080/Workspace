// 导入koa
const Koa = require("koa")

// 导入 总耗时中间件
const durationFn = require("./middleware/koa_response_duration")
const headerFn = require("./middleware/koa_response_header")
const dataFn = require("./middleware/koa_response_data")

// 创建 koa 对象
const app = new Koa()

// 绑定 第一层 中间件
app.use(durationFn)

// 绑定 第二层 中间件
app.use(headerFn)

// 绑定 第三层 中间件
app.use(dataFn)

// 监听8081端口
app.listen(8081, () => {
  console.log("服务器监听 8081 端口")
})