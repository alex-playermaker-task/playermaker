package playermaker.com.playermakertask.listener;

import playermaker.com.playermakertask.dto.PlayerTopResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaListenerService {
    void listenFinalResponse(ConsumerRecord<String, PlayerTopResponse> record);
}
