# Netty-IM

> 项目实现参照掘金小册

## 运行
+ 打包之后，在`target`中使用 `Java -jar `运行包含依赖的`jar`包(xxx-with-dependence-**)

## 服务端

+ 默认端口：`8000`
+ 默认地址：`127.0.0.1`

## 客户端

+ 操作指令
  1. `logout:`退出登录
  2. `sendToUser:`发送消息给指定用户
  3. `createGroup:`创建群聊
  4. `joinGroup:`加入群聊
  5. `quitGroup:`退出群聊
  6. `listGroupMembers:`群成员列表
  7. `sendToGroup:`发送消息给指定群聊
