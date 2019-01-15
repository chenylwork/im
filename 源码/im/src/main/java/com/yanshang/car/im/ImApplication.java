package com.yanshang.car.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImApplication.class, args);
	}
}
//	@Value("${websocket.port}")
//	private int port;
//	private static int PORT;
//	@PostConstruct
//	public void init() {
//		PORT = port;
//	}
//	public static void main(String[] args) {
//		SpringApplication.run(ImApplication.class, args);
//////		int port = 8887;
////		WebSocketServerImpl server = null;
////		try {
////			server = new WebSocketServerImpl(PORT);
////		} catch (UnknownHostException e) {
////			e.printStackTrace();
////		}
////		server.start();
////		System.out.println("房间已开启，等待客户端接入，端口号: " + server.getPort());
//	}
//
//}

