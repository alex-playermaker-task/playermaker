package playermaker.com.playermakertask.service.impl;

import playermaker.com.playermakertask.dto.PlayerTopResponse;
import playermaker.com.playermakertask.listener.KafkaListenerService;
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
    private RedisTemplate<String, PlayerTopResponse> playerTopResponseRedisTemplate;

    @Autowired
    private WebSocketController webSocketController;

    @Override
    @KafkaListener(topics = "player.response.final", groupId = "response-group")
    public void listenFinalResponse(ConsumerRecord<String, PlayerTopResponse> record) {
        playerTopResponseRedisTemplate.opsForValue().set(record.key(), record.value(), 1, TimeUnit.HOURS);
        webSocketController.sendResult(record.key(), record.value());
    }
}
