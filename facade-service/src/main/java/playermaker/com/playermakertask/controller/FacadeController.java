package playermaker.com.playermakertask.controller;

import playermaker.com.playermakertask.dto.*;
import playermaker.com.playermakertask.service.FacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facade")
public class FacadeController {

    @Autowired
    private FacadeService facadeService;

    @PostMapping("/process")
    public ResponseEntity<String> processGameData(@RequestBody PlayerRequest request) {
        return facadeService.processGameData(request);
    }

    @GetMapping("/result/{requestId}")
    public ResponseEntity<?> getResult(@PathVariable String requestId) {
        return facadeService.getResult(requestId);
    }
}
