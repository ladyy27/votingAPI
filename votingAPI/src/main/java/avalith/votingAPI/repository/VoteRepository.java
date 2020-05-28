package avalith.votingAPI.repository;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findById(long id);

    Vote save(Vote vote);

    List<Vote> findAll();

    List<Vote> findByIssuerAndRecipientAndArea(User issuer, User recipient, Area area);

    @Query("SELECT v.area, v.recipient, COUNT(v) AS noVotos\n" +
            "FROM Vote v\n" +
            "WHERE v.area = :area\n" +
            "GROUP BY v.recipient\n" +
            "ORDER BY noVotos DESC")
    List<Vote> findMostVotedByArea(Area area);

    @Query("SELECT FUNCTION('YEAR',v.date), FUNCTION('MONTH',v.date), v.recipient, COUNT(v) AS noVotos\n" +
            "FROM Vote v\n" +
            "WHERE FUNCTION('YEAR',v.date) = :year AND \n" +
            "FUNCTION('MONTH',v.date) = :month \n" +
            "GROUP BY v.recipient\n" +
            "ORDER BY noVotos DESC")
    List<Vote> findMostVotedByYearAndMonth(int year, int month);
}
