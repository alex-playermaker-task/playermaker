package playermaker.com.playermakertask.controller;

import playermaker.com.playermakertask.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import playermaker.com.playermakertask.service.FacadeService;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private FacadeService facadeService;

    @MessageMapping("/top-players")
    @SendTo("/topic/players")
    public void process(PlayerRequest request) {
        facadeService.processGameData(request);
    }

    public void sendResult(String requestId, PlayerTopResponse response) {
        this.template.convertAndSend("/topic/players/" + requestId, response);
    }
}
