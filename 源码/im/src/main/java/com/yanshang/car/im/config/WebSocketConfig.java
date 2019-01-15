package com.yanshang.car.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/*
 * @ClassName WebSocketConfig
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 13:20
 * @Version 1.0
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
