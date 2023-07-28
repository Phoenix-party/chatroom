package com.example.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static long HEART_BEAT=10000;
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置消息代理
        registry.enableSimpleBroker("/topic")
                .setHeartbeatValue(new long[]{HEART_BEAT, HEART_BEAT})
                .setTaskScheduler(taskScheduler());
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 註冊 Stomp 端點
        registry.addEndpoint("/ws").withSockJS();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
//可使用版本