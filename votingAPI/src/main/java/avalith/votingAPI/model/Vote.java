package avalith.votingAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;

    public Vote() {
    }

    public Vote(User issuer, User recipient, Area area, String comment, LocalDate date) {
        this.issuer = issuer;
        this.recipient = recipient;
        this.area = area;
        this.comment = comment;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_generator")
    @SequenceGenerator(name = "vote_generator", initialValue = 100)
    @Column(unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="ISSUER_ID", nullable=false)
    private User issuer;

    @ManyToOne
    @JoinColumn(name="RECIPIENT_ID", nullable=false)
    private User recipient;

    @ManyToOne
    @JoinColumn(name="AREA_ID", nullable=false)
    private Area area;

    @Column
    private String comment;

    @Column
    private LocalDate date;

}
