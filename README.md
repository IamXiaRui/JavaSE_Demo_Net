##基本概念

１、网络模型：ＴＣＰ／ＩＰ参考模型、ＯＳＩ参考模型


２、网络通讯的要素：
ＩＰ地址：每台联网设备的标识。本地回环地址：１２７．０．０．１，名字：ｌｏｃａｌｈｏｓｔ
端口号：逻辑端口，网络应用程序的标识，０～６５５３５（０～１０２４一般为系统保留端口）
传输协议：通信的规则，ＵＤＰ、ＴＣＰ／ＩＰ

３、拿到任意一台主机的ＩＰ地址：

	static InetAddress	getByName(String host) 
	          在给定主机名的情况下确定主机的 IP 地址。




##Ｓｏｃｋｅｔ

１、通常也称作"套接字"，用于描述IP地址和端口，是一个通信链的句柄，可以用来实现不同虚拟机或不同计算机之间的通信。

２、Ｓｏｃｋｅｔ就是为网络服务提供的一种机制

３、通信的两端都有Ｓｏｃｋｅｔ

４、网络通信就是Ｓｏｃｋｅｔ之间的通信

５、数据在两个Ｓｏｃｋｅｔ之间通过ＩＯ传输。




##ＵＤＰ

１、概念：

>>①将数据及源和目的封装到数据包中，不需要建立连接

>>②每个数据包的大小限制在６４Ｋ内

>>③因为没有建立连接，所以是不可靠协议

>>④不需要建立连接，速度快

２、ＵＤＰ传输

	 void	receive(DatagramPacket p) 
		          从此套接字接收数据报包。
	 void	send(DatagramPacket p) 
	          从此套接字发送数据报包。

###发送数据步骤：
>>1、建立UDP Socket服务 

>>2、提供数据，并将数据封装到数据包中

	DatagramPacket(byte[] buf, int length, InetAddress address, int port) 
	          构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。

>>3、通过Socket服务的发送功能，将数据包发出去

>>4、关闭资源

>>5、记得抛出异常

###接收数据步骤：
>>1、定义UDP的Socket服务，并建立端点

	//创建UDP Socket,建立端点
	DatagramSocket ds = new DatagramSocket(1000);

>>2、定义一个数据包，存储接收到的字节数据。数据包对象中有更多的功能可以提取自己数据中的不同数据信息。

>>3、通过Socket服务的receive方法将受到的数据存入已经定义好的数据包中

>>4、通过数据包对象的特有功能，将不同数据取出，打印在控制台上。

>>5、关闭资源

>>6、记得抛出异常



##ＴＣＰ
１、概念
>>①建立连接，形成传输数据的通道

>>②在连接中进行大数据量的传输
>
>>③通过三次握手完成连接，是可靠协议
>
>>④必须建立连接，效率会稍低

２、TCP传输

>>①TCP分为客户端和服务端
>
>>②客户端对应的对象是 Socket , 服务器端是ServerSocket

###客户端：

1、Socket对象创建的时候，就可以去连接指定主机。因为Tcp是面向连接的，所以在建立Socket服务时，就要有服务端的存在，并且连接成功，形成通路后，在该通道进行数据的传输。

2、步骤：
>>①创建Socket服务，并指定要连接的主机和端口；

	Socket(InetAddress address, int port) 
	          创建一个流套接字并将其连接到指定 IP 地址的指定端口号。



>>②为了发送数据，需要获取Socket流中的输出流；

	OutputStream	getOutputStream() 
		          返回此套接字的输出流。


>>③将数据写入流中，并关闭资源

>>④记得抛出异常

###服务端：

>>①建立服务端的Socket服务，ServerSocket();并监听一个端口号；

	ServerSocket(int port) 
	          创建绑定到特定端口的服务器套接字。

>>②获取连接过来的客户端对象，这个方法是阻塞式的，如果没有连接就会一直等待；
	
	 Socket	accept() 
		          侦听并接受到此套接字的连接。

>>③如果客户端发送数据，服务端需要使用对应的客户端对象并获取到该客户端对象的读取流来读取发送过来的数据。

	 InputStream	getInputStream() 
		          返回此套接字的输入流。
	 InetAddress	getInetAddress() 
		          返回套接字连接的地址。


>>④关闭服务端（可选操作），记得抛出异常，也可以关闭客户端。

注意：客户端和服务端进行键盘录入加入缓冲区的时候，必须加入结束符，因为客户端和服务端都是阻塞式方法，可能会出现死等的现象．


结束标记符：　

	 void	shutdownInput() 
		          此套接字的输入流置于“流的末尾”。
	 void	shutdownOutput() 
	          禁用此套接字的输出流。


３、并发访问服务端

将每个客户端封装到一个单独的线程中，使得服务端可以同时处理多个客户请求。

如何定义线程？

只要明确了每一个客户端要在服务端执行的代码，并将该代码存入ｒｕｎ方法中。


##ＵＲＬ


	 String	getFile() 
		          获取此 URL 的文件名。
	 String	getHost() 
		          获取此 URL 的主机名（如果适用）。
	 String	getPath() 
		          获取此 URL 的路径部分。
	 int	getPort() 
		          获取此 URL 的端口号。
	 String	getProtocol() 
		          获取此 URL 的协议名称。
	 String	getQuery() 
	          获取此 URL 的查询部分。



１、ｇｅｔＦｉｌｅ（）　和　ｇｅｔＰａｔｈ（）区别：

>>ｇｅｔＰａｔｈ（）：　获得路径，不包含参数

>>ｇｅｔＦｉｌｅ（）：　获得整个网址信息，包含参数

>>多余的参数信息就是　：　ｇｅｔＱｕｅｒｙ（）获取的信息。




２、获取主机连接对象

	 URLConnection	openConnection() 
	          返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。








