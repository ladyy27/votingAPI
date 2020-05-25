package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;

import java.util.List;

public interface VoteService {

    Vote getVote(long id);

    List<Vote> getVotes();

    Vote save(Vote vote);

    List<Vote> getVotesByIssuerAndRecipientAndArea(User issuer, User recipient, Area area);

}
