package avalith.votingAPI.controller;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import avalith.votingAPI.model.VoteDTO;
import avalith.votingAPI.repository.UserRepository;
import avalith.votingAPI.service.AreaService;
import avalith.votingAPI.service.AuthenticationService;
import avalith.votingAPI.service.UserService;
import avalith.votingAPI.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/votes")
@CrossOrigin
public class VoteController {

    @Autowired
    private AuthenticationService authenticationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private VoteService voteService;

    /*public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }*/

    public VoteController(UserRepository userRepository, VoteService voteService) {
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Vote> newVote (@RequestBody VoteDTO voteDTO) {

        long authenticadedUser = currentIdUser();
        if (voteDTO.getRecipient_id() != authenticadedUser){

            //If there is no comment, comment will be an empty String
            String comment = voteDTO.getComment();
            if (comment == null) { comment = ""; }

            //If date is not given from the Client, the Server applies the system date.
            LocalDate date = voteDTO.getDate();
            if (date == null) { date = LocalDate.now(ZoneId.systemDefault()); }


            User issuerUser = userService.getUser(authenticadedUser);
            User recipientUser = userService.getUser(voteDTO.getRecipient_id());
            Area chosenArea = areaService.getArea((voteDTO.getArea_id()));
            if (issuerUser == null || recipientUser == null || chosenArea == null) { return ResponseEntity.notFound().build(); }

            String currentDate = dateToYearMonth(date);
            System.out.println(currentDate);

            List<Vote> votesFilter = voteService.getVotesByIssuerAndRecipientAndArea(issuerUser, recipientUser, chosenArea);
            for (Vote vote : votesFilter) {
                System.out.println(vote);
                String dateOfVote = dateToYearMonth(vote.getDate());
                System.out.println(dateOfVote);
                if (currentDate.equals(dateOfVote)){
                    return ResponseEntity.badRequest().build();
                }
            }
            Vote newVote = new Vote();
            newVote.setIssuer(issuerUser);
            newVote.setRecipient(recipientUser);
            newVote.setArea(chosenArea);
            newVote.setComment(comment);
            newVote.setDate(date);

            final Vote voteToSave = voteService.save(newVote);
            return new ResponseEntity<>(voteToSave, HttpStatus.OK);
        }
        else{return ResponseEntity.badRequest().build();}
    }

    //cambiar rol
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping
    public List<Vote> getVotes() {
        final List<Vote> votes = voteService.getVotes();
        return votes;
    }

    public long currentIdUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        return user.getId();
    }

    //Input: Local Date , Output: String with month & year in format: aaaa-mm
    public String dateToYearMonth(LocalDate date){
        String stringDate = date.toString();
        String yearAndMonth = stringDate.substring(0,7);
        return yearAndMonth;
    }


}
