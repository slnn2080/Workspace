const { join } = require("path")
const { getFileJsonData } = require("../utils/file_utils.js")
/*
  创建 WebSocket 对象 用于主动向客户端推送数据

  疑问:
    后台有两个端口?
    1. 正常后台程序的端口 8081
    2. WebSocket监听的端口 8082
*/

const WebSocket = require("ws")
// 创建websocket服务端对象
const wss = new WebSocket.Server({
  port: 8082
})


// 服务端开启Websocket监听: 将监听事件的相关逻辑封装到 listen 方法中, 只有执行了listen函数 才能执行websocket的链接 和 数据的接收
function listen() {
  // 监听客户端的连接
  wss.on("connection", client => {
    // client: 客户端连接的socket对象
    console.log("有客户端进行连接")


    // 接收数据: 监听 客户端 是否向 服务端 发送数据 当有数据到服务端的时候 触发回调
    client.on("message", async msg => {

      /*
        该回调说明 客户端发送数据过来了 我们要在这里完成如下的判断
        1. action: getData -> 读取图标数据 响应会客户端
        2. action: 非getData -> 将客户端发送的消息 直接转发给其它客户端
      */

      // 将客户端传递过来的数据 转换为对象 注意msg为buf
      const param = JSON.parse(msg.toString())
      if (param.action === "getData") {
        /*
        1. 取出数据中 chartName 字段
        2. 拼接json文件路径
        3. 读取该文件的内容
        4. 在接收到的数据基础上 增加 data 字段
        */

        let filePath = `../data/${param.chartName}.json`
        // 绝对路径
        filePath = join(__dirname, filePath)

        // 获取图表数据
        const ret = await getFileJsonData(filePath)

        // 响应回客户端, 增加data字段
        param.data = ret
        client.send(JSON.stringify(param))

      } else {
        // 原封不动的将所接收到的数据转发给每一个处于连接状态的客户端
        // wss.clients 为 处于连接的每一个客户端
        wss.clients.forEach(client => client.send(msg.toString()))
      }
    })

  })
}

module.exports = { listen }


