package avalith.votingAPI.controller;

import avalith.votingAPI.model.*;
import avalith.votingAPI.repository.RoleRepository;
import avalith.votingAPI.repository.UserRepository;
import avalith.votingAPI.security.AuthorizationRequest;
import avalith.votingAPI.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteControllerTest {
    private static final long ISSUER_ID = 4;

    public static final String ISSUER_NAME = "user4";

    public static final String ISSUER_PASSWORD = "$2a$10$c7u7NqSxIEPobDAYfJiM.ueUWuDDk5lSkvxWi87UznOKOFV.mmp2C";

    private static final long RECIPIENT_ID = 4;

    public static final String RECIPIENT_NAME = "user5";

    public static final String RECIPIENT_PASSWORD = "$2a$10$xoTjYxnlR3upOAn/h30F1u9gVjIHH0C8zmCMhsKlToncJpyY.rEcu";

    private static final long AREA_ID = 1;

    public static final String AREA_NAME = "Team player";

    public static final LocalDate DATE = LocalDate.now(ZoneId.systemDefault());

    @Mock
    private VoteServiceImpl voteService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AreaServiceImpl areaService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private VoteController voteController;

    @Test
    public void callServiceSaveUser() {

        final VoteDTO voteDTO = VoteDTO.builder().issuer_id(ISSUER_ID).recipient_id(RECIPIENT_ID).area_id(AREA_ID).build();
        System.out.println(voteDTO.getIssuer_id());

        final User issuer = User.builder().id(ISSUER_ID).name(ISSUER_NAME).password(ISSUER_PASSWORD).build();
        when(userRepository.findById(voteDTO.getIssuer_id())).thenReturn(issuer);

        final User recipient = User.builder().id(RECIPIENT_ID).name(RECIPIENT_NAME).password(RECIPIENT_PASSWORD).build();
        when(userRepository.findById(voteDTO.getRecipient_id())).thenReturn(recipient);
        System.out.println(issuer);

        final Area area = Area.builder().id(AREA_ID).name(AREA_NAME).build();
        when(areaService.getArea(voteDTO.getArea_id())).thenReturn(area);
        System.out.println(area);

        final Vote voteToSave = Vote.builder().id(10).issuer(issuer).recipient(recipient).area(area).date(DATE).build();
        final Vote savedVote = Vote.builder().id(10).issuer(issuer).recipient(recipient).area(area).date(DATE).build();
        System.out.println(savedVote);

        when(voteService.save(voteToSave)).thenReturn(savedVote);

        final ResponseEntity<Vote> voteResponse = voteController.newVote(voteDTO);
        assertThat(voteResponse.getBody()).isEqualTo(savedVote);
        assertThat(voteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
