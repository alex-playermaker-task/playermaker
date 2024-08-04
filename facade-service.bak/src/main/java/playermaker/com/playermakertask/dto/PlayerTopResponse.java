package playermaker.com.playermakertask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTopResponse {
    private int requiredTopPlayers;
    private List<String> mostParticipatedPlayers;

}
