package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;
import avalith.votingAPI.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/***
 * VoteServiceImpl implements all VoteService methods
 */
@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    /***
     * getVote(long id) get one Vote object filtering it by id
     * @param id
     * @return Vote vote
     */
    @Override
    public Vote getVote(long id) {
        return voteRepository.findById(id);
    }


    /***
     * getVote(long id) get all Vote objects
     * @return <List> Vote
     */
    @Override
    public List<Vote> getVotes() {
        return voteRepository.findAll();
    }

    /***
     * save a new Vote object
     * @param vote
     * @return Vote vote
     */
    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    /***
     * getVotesByIssuerAndRecipientAndArea gets all Vote objects filtering by issuer, recipient, area
     * @param issuer
     * @param recipient
     * @param area
     * @return List<Vote>
     */
    @Override
    public List<Vote> getVotesByIssuerAndRecipientAndArea (User issuer, User recipient, Area area){
        return voteRepository.findByIssuerAndRecipientAndArea(issuer, recipient,area);
    }

    /***
     * getMostVotedByArea gets area, recipient, number of votes in Vote objects grouped by recipient
     * @param area
     * @return area, recipient, No votes
     */
    @Override
    public List<Vote> getMostVotedByArea(Area area){
        return voteRepository.findMostVotedByArea(area);
    }

    /***
     * getMostVotedByYearAndMonth gets year, month, recipient, number of votes in Vote objects grouped by year & month
     * @param year
     * @param month
     * @return year, month, recipient, number of votes
     */
    @Override
    public List<Vote> getMostVotedByYearAndMonth(int year, int month){
        return voteRepository.findMostVotedByYearAndMonth(year,  month);
    }

    /***
     * dateToYearMonth turns a complete date with format ('yyyy-MM--dd') into a string with format ''yyyy-MM'
     * @param date
     * @return String yearMonth
     */
    @Override
    public String dateToYearMonth(LocalDate date){
        String stringDate = date.toString();
        String yearAndMonth = stringDate.substring(0,7);
        return yearAndMonth;
    }

    /***
     * checkOneVotePerMonth returns true if the Vote object already exists in database filtering by date, issuer, recipient and area. Otherwise, returns false
     * @param date
     * @param issuer
     * @param recipient
     * @param area
     * @return boolean
     */
    @Override
    public boolean checkOneVotePerMonth(LocalDate date, User issuer, User recipient, Area area){
        String currentDate = dateToYearMonth(date);
        List<Vote> votesFilter = getVotesByIssuerAndRecipientAndArea(issuer, recipient, area);
        for (Vote vote : votesFilter) {
            String dateOfVote = dateToYearMonth(vote.getDate());
            System.out.println("*********DAY OF VOTE***********");
            System.out.println(dateOfVote);
            if (currentDate.equals(dateOfVote)){
                return true;
            }
        }
        return false;
    }

}
