// 中间件就是一个函数 我们暴露一个函数就可以
module.exports = async (ctx, next) => {

  // 记录开始时间
  const start = Date.now()

  // 让内层中间件得到执行
  await next()

  // 记录结束的时间
  const end = Date.now()

  // 设置响应头 X-Response-Time
  const duration = end - start
  
  // ctx.set 设置响应头
  ctx.set('X-Response-Time', duration + 'ms')
}