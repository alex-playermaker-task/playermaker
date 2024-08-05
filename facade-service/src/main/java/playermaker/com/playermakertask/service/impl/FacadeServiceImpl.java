package playermaker.com.playermakertask.service.impl;

import playermaker.com.playermakertask.dto.PlayerRequest;
import playermaker.com.playermakertask.dto.PlayerTopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.service.FacadeService;

import java.util.UUID;

@Service
public class FacadeServiceImpl implements FacadeService {

    @Autowired
    private KafkaTemplate<String, PlayerRequest> kafkaTemplate;

    @Autowired
    private RedisTemplate<String, PlayerTopResponse> playerTopResponseRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> generalRedisTemplate;

    @Override
    public ResponseEntity<String> processGameData(PlayerRequest request) {
        validateRequest(request);
        String requestId = UUID.randomUUID().toString();
        generalRedisTemplate.opsForValue().set(requestId + ":request", request);
        kafkaTemplate.send("player.participation.raw", requestId, request);
        return ResponseEntity.accepted().body(requestId);
    }

    @Override
    public ResponseEntity<PlayerTopResponse> getResult(String requestId) {
        PlayerTopResponse response = playerTopResponseRedisTemplate.opsForValue().get(requestId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    private void validateRequest(PlayerRequest request) {
        if (request.getRequiredTopPlayers() <= 0 || request.getParticipatedPlayers().isEmpty()) {
            throw new IllegalArgumentException("Invalid input data");
        }
    }
}
