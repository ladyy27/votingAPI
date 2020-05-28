package avalith.votingAPI.repository;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class VoteRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getVoteTest() {
        final Vote vote = voteRepository.findById(1);

        assertThat(vote).isNotNull();
        assertThat(vote.getId()).isEqualTo(1);
    }

    @Test
    public void getVotesTest() {
        final List<Vote> votes = voteRepository.findAll();

        assertThat(votes).isNotNull();
    }

    @Test
    public void saveVoteTest() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleById(2));

        User newIssuer = new User(3,"NEWUSER1", "PASSWORD1", roles);

        User newRecipient = new User(2,"NEWUSER2", "PASSWORD2", roles);

        Area area = areaRepository.findById(1);

        LocalDate date = LocalDate.now(ZoneId.systemDefault());

        String comment = "COMMENT1";

        Vote vote = new Vote(100, newIssuer, newRecipient, area, comment, date);

        Vote savedVote = voteRepository.save(vote);

        final Vote retrievedVote = voteRepository.findById(savedVote.getId());

        assertThat(retrievedVote).isEqualTo(savedVote);
    }

    @Test
    public void getByIssuerAndRecipientAndArea() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleById(2));

        User newIssuer = new User(100,"NEWUSER1", "PASSWORD1", roles);

        User newRecipient = new User(101,"NEWUSER2", "PASSWORD2", roles);

        Area area = areaRepository.findById(1);

        List<Vote> filterByIssuerAndRecipientAndArea =  voteRepository.findByIssuerAndRecipientAndArea(newIssuer, newRecipient, area);

        assertThat(filterByIssuerAndRecipientAndArea).isNotNull();
    }

    @Test
    public void getMostVotedByArea() {

        Area area = areaRepository.findById(1);

        List<Vote> filterByIssuerAndRecipientAndArea =  voteRepository.findMostVotedByArea(area);

        assertThat(filterByIssuerAndRecipientAndArea).isNotNull();
    }

    @Test
    public void getMostVotedByYearAndMonth() {

        List<Vote> filterByIssuerAndRecipientAndArea =  voteRepository.findMostVotedByYearAndMonth(2020, 05);

        assertThat(filterByIssuerAndRecipientAndArea).isNotNull();
    }
}
