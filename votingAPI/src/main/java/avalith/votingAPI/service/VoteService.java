package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.User;
import avalith.votingAPI.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    /***
     * getVote(long id) Service get one Vote object filtering it by id
     * @param id
     * @return Vote vote
     */
    Vote getVote(long id);

    /***
     * getVote(long id) Service get all Vote objects
     * @return <List> Vote
     */
    List<Vote> getVotes();

    /***
     * save a new Vote object
     * @param vote
     * @return Vote vote
     */
    Vote save(Vote vote);

    /***
     * getVotesByIssuerAndRecipientAndArea Service gets all Vote objects filtering by issuer, recipient, area
     * @param issuer
     * @param recipient
     * @param area
     * @return List<Vote>
     */
    List<Vote> getVotesByIssuerAndRecipientAndArea(User issuer, User recipient, Area area);

    /***
     * getMostVotedByArea Service gets area, recipient, number of votes in Vote objects grouped by recipient
     * @param area
     * @return area, recipient, No votes
     */
    List<Vote> getMostVotedByArea(Area area);

    /***
     * getMostVotedByYearAndMonth Service gets year, month, recipient, number of votes in Vote objects grouped by year & month
     * @param year
     * @param month
     * @return year, month, recipient, number of votes
     */
    List<Vote> getMostVotedByYearAndMonth(int year, int month);

    /***
     * dateToYearMonth Service turns a complete date with format ('yyyy-MM--dd') into a string with format ''yyyy-MM'
     * @param date
     * @return String yearMonth
     */
    String dateToYearMonth(LocalDate date);

    /***
     * checkOneVotePerMonth Service returns true if the Vote object already exists in database filtering by date, issuer, recipient and area. Otherwise, returns false
     * @param date
     * @param issuer
     * @param recipient
     * @param area
     * @return boolean
     */
    boolean checkOneVotePerMonth(LocalDate date, User issuer, User recipient, Area area);

}
