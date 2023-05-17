// 处理业务逻辑的中间件,读取某个json文件的数据
const path = require('path')

// 读取json文件的方法
const fileUtils = require('../utils/file_utils')

module.exports = async (ctx, next) => {
  // 根据url 整理出 json数据文件的位置
  const url = ctx.request.url 
  // console.log(url)
  // /api/seller

  console.log(url)

  // 去掉 /api 不分
  let filePath = url.replace('/api', '') //  /seller
  
  // 拼接路径
  filePath = '../data' + filePath + '.json'
  // console.log(filePath)
  // ../data/seller.json
  
  // 当前文件所在的目录 + ../ 会减少一层目录 最终实现拼接的操作
  filePath = path.join(__dirname, filePath)
  // console.log(filePath)
  // /Users/sam/Desktop/Sam/Demo/Front/Echarts_Product/server/data/seller.json

  try {
    const ret = await fileUtils.getFileJsonData(filePath)
    ctx.response.body = ret
  } catch (error) {
    const errorMsg = {
      message: '读取文件内容失败, 文件资源不存在',
      status: 404
    }
    // 将读取的数据响应回前台
    ctx.response.body = JSON.stringify(errorMsg)
  }
  
  // 最好每个中间件都执行下 next()
  await next()
}