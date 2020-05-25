package avalith.votingAPI.repository;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface VoteRepository extends JpaRepository<Vote, Long> {
    //Vote findByArea(long id_area);

    Vote findById(long id);

    Vote save(Vote vote);

    List<Vote> findAll();

    List<Vote> findByIssuerAndRecipientAndArea(User issuer, User recipient, Area area);


}
