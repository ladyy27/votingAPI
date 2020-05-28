package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import avalith.votingAPI.repository.AreaRepository;
import avalith.votingAPI.repository.RoleRepository;
import avalith.votingAPI.repository.UserRepository;
import avalith.votingAPI.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VoteServiceTest {

    private static final String USER_NAME = "user8";

    private static final long USER_ID = 1L;

    private static final String USER_PASSWORD = "password8";

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private VoteServiceImpl voteService;

    @Test
    public void getVoteUsingRepositoryTest() {
        voteService.getVote(1);

        verify(voteRepository).findById(1);
    }

    @Test
    public void getVotesUsingRepositoryTest() {
        voteService.getVotes();

        verify(voteRepository).findAll();
    }

    @Test
    public void filterMostVotedByIssuerAndRecipientAndArea() {

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleById(2));

        User newIssuer = new User(100,"NEWUSER1", "PASSWORD1", roles);

        User newRecipient = new User(101,"NEWUSER2", "PASSWORD2", roles);

        Area area = areaRepository.findById(1);

        List<Vote> filterByIssuerAndRecipientAndArea =  voteService.getVotesByIssuerAndRecipientAndArea(newIssuer, newRecipient, area);

        assertThat(filterByIssuerAndRecipientAndArea).isNotNull();
    }

    @Test
    public void filterMostVodedByArea() {

        Area area = areaRepository.findById(1);

        List<Vote> filterByIssuerAndRecipientAndArea =  voteService.getMostVotedByArea(area);

        assertThat(filterByIssuerAndRecipientAndArea).isNotNull();
    }

    @Test
    public void checkOneVotePerMonth() {
        LocalDate date = LocalDate.now(ZoneId.systemDefault());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleById(2));

        User newIssuer = new User(100,"NEWUSER1", "PASSWORD1", roles);

        User newRecipient = new User(101,"NEWUSER2", "PASSWORD2", roles);

        Area area = areaRepository.findById(1);

        boolean doesExistThisVote = voteService.checkOneVotePerMonth(date, newIssuer, newRecipient, area);

        assertThat(doesExistThisVote).isNotEqualTo(true);
    }

}
