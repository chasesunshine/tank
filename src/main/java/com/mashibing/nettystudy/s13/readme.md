学习使用Codec
	定义TankMsg x, y
	TankMsgEncoder负责编码
	TankMsgDecoder负责解码
	将Encoder加入客户端Channel处理链
	将Decoder加入服务器Channel处理链
	在客户端channelActive的时候发送一个TankMsg
	观察服务器是否接收正确