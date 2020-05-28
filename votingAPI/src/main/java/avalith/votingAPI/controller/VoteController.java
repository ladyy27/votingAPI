package avalith.votingAPI.controller;

import avalith.votingAPI.model.*;
import avalith.votingAPI.service.AreaService;
import avalith.votingAPI.service.AuthenticationService;
import avalith.votingAPI.service.UserService;
import avalith.votingAPI.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/votes")
@CrossOrigin
public class VoteController {

    @Autowired
    private AuthenticationService authenticationFacade;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private AreaService areaService;

    public VoteController( AuthenticationService authenticationFacade, VoteService voteService, UserService userService, AreaService areaService) {
        this.authenticationFacade = authenticationFacade;
        this.voteService = voteService;
        this.userService = userService;
        this.areaService = areaService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Vote> getVotes() {
        final List<Vote> votes = voteService.getVotes();
        return votes;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/area/{area_id}")
    public List<Vote> mostVotedByArea(@PathVariable long area_id) {
        Area area = areaService.getArea(area_id);
        final List<Vote> votesByArea = voteService.getMostVotedByArea(area);
        return votesByArea;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/year/{year}/month/{month}")
    public List<Vote> mostVotedByMonthAndYear(@PathVariable int year, @PathVariable int month ) {
        final List<Vote> votesByYearAndMonth = voteService.getMostVotedByYearAndMonth(year, month);
        return votesByYearAndMonth;
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE') OR hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Vote> newVote (@RequestBody VoteDTO voteDTO) {
        //Formatting Vote object in order to save
        Authentication authentication = authenticationFacade.getAuthentication();
        User currentUser = userService.getUserByName(authentication.getName());
        User issuerUser = userService.getUser(currentUser.getId());
        User recipientUser = userService.getUser(voteDTO.getRecipient_id());
        Area chosenArea = areaService.getArea(voteDTO.getArea_id());
        String comment = voteDTO.getComment();
        LocalDate date = voteDTO.getDate();

        //If there is no comment, comment will be an empty String
        if (comment == null) { comment = ""; }

        //If date is not given from the Client, the Server applies the system date.
        if (date == null) { date = LocalDate.now(ZoneId.systemDefault()); }

        //If any of those required params is null, the request will not succeed
        if (issuerUser == null || recipientUser == null || chosenArea == null){
            return ResponseEntity.badRequest().build(); }

        // The recipient user should not to be the current session user
        if (recipientUser == issuerUser) {
            return ResponseEntity.badRequest().build();}

        // It is allowed only one vote per month
        if (voteService.checkOneVotePerMonth(date, issuerUser, recipientUser, chosenArea)){
            return ResponseEntity.badRequest().build(); }

        Vote newVote = new Vote();
        newVote.setIssuer(issuerUser);
        newVote.setRecipient(recipientUser);
        newVote.setArea(chosenArea);
        newVote.setComment(comment);
        newVote.setDate(date);

        final Vote voteToSave = voteService.save(newVote);
        return new ResponseEntity<>(voteToSave, HttpStatus.OK);
    }
}
