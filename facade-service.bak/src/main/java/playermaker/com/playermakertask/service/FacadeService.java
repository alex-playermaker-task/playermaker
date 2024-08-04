package playermaker.com.playermakertask.service;

import playermaker.com.playermakertask.dto.*;
import org.springframework.http.ResponseEntity;

public interface FacadeService {
    ResponseEntity<String> processGameData(PlayerRequest request);
    ResponseEntity<PlayerTopResponse> getResult(String requestId);
}
