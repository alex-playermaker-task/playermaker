package playermaker.com.playermakertask.service.impl;

import playermaker.com.playermakertask.dto.PlayerTopResponse;
import playermaker.com.playermakertask.service.KafkaListenerService;
import playermaker.com.playermakertask.controller.WebSocketController;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KafkaListenerServiceImpl implements KafkaListenerService {

    @Autowired
    private RedisTemplate<String, PlayerTopResponse> redisTemplate;

    @Autowired
    private WebSocketController webSocketController;

    @Override
    @KafkaListener(topics = "player.response.final", groupId = "response-group")
    public void listenFinalResponse(ConsumerRecord<String, PlayerTopResponse> record) {
        // Сохранение результата в Redis
        redisTemplate.opsForValue().set(record.key(), record.value(), 1, TimeUnit.HOURS);

        // Отправка уведомления клиенту через WebSocket
        webSocketController.sendResult(record.key(), record.value());
    }
}
