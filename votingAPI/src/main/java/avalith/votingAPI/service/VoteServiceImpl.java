package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import avalith.votingAPI.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVote(long id) {
        return voteRepository.findById(id);
    }


    @Override
    public List<Vote> getVotes() {
        return voteRepository.findAll();
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotesByIssuerAndRecipientAndArea (User issuer, User recipient, Area area){
        return voteRepository.findByIssuerAndRecipientAndArea(issuer, recipient,area);
    }

}
