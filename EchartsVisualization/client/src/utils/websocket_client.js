/*
1. 定义类 SocketService 并定义成单例设计模式
2. 定义链接服务器的方法 connect
3. 监听事件
4. 存储回调函数
5. 接收数据的处理
6. 定义发送数据的方法
7. 挂载socketservice对象到vue原型上
*/

export  default class SocketService {
  // 单例设计模式
  static instance = null

  /*
  getter属性
    在 JavaScript 中，类的静态方法中的 this 关键字指向类本身 而不是类的实例对象
    静态方法是直接通过类名调用的，而不需要创建类的实例
  */
  static get Instance() {
    if(!this.instance) {
      this.instance = new SocketService()
    }

    return this.instance
  }

  // websocket对象
  ws = null

  // 创建存储各个组件处理数据的方法
  callBackMapping = {}

  // 标识是否连接成功
  connected = false

  // 记录重试的次数
  sendRetryCount = 0
  // 记录重新连接服务器的尝试的次数
  connectRetryCount = 0

  // 注册组件处理数据的方法
  registerCallBack(socketType, callBack) {
    this.callBackMapping[socketType] = callBack
  }

  // 移除注册的指定方法
  unRegisterCallBack(socketType) {
    this.callBackMapping[socketType] = null
  }

  // 定义链接服务器的方法:
  connect() {
    // 判断浏览器是否支持websocket
    if (!window.WebSocket) {
      console.log("您的浏览器不支持websocket")
      return
    }

    this.ws = new WebSocket("ws://localhost:8082")

    // 监听链接成功的事件
    this.ws.onopen = () => {
      console.log("连接服务端 成功 了")
      this.connected = true
      this.connectRetryCount = 0
    }

    // 连接服务器失败 或 链接成功后服务器关闭的情况 该回调也会得到执行
    this.ws.onclose = () => {
      console.log("连接服务端 失败 了")
      this.connected = false

      // 每次连接失败都要进行++
      this.connectRetryCount++

      // 再次重连
      setTimeout(() => {
        this.connect()
      }, this.connectRetryCount * 500)

    }

    this.ws.onmessage = e => {

      let param = JSON.parse(e.data)

      const { action, socketType} = param

      if (socketType && this.callBackMapping[socketType]) {

        if (action === "getData") {
          // 说明每一个组件想要获取图表数据
          let { data } = param
          data = JSON.parse(data)

          // 当前对象调用的函数
          this.callBackMapping[socketType].call(this, data)

        } else if (action === "fullScreen") {

        } else if (action === "themeChange") {

        }
      }
    }
  }

  // 定义向服务器发送数据的方法
  send(data) {
    // 判断此时此刻有没有连接成功
    if (this.connected) {
      // 发送成功的时候 该值 重置为0
      this.sendRetryCount = 0
      this.ws.send(JSON.stringify(data))
    } else {
      this.sendRetryCount++
      // 进行延迟的重试操作
      setTimeout(() => {
        this.send(data)
      }, this.sendRetryCount * 500)
    }
  }
}