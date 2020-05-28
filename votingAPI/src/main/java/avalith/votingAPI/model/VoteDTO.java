package avalith.votingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
public class VoteDTO {

    private long issuer_id;

    private long recipient_id;

    private long area_id;

    private String comment;

    private LocalDate date;

}
