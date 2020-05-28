package avalith.votingAPI.repository;

import avalith.votingAPI.model.Area;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Rollback
public class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void getVoteTest() {
        final Area area = areaRepository.findById(1);

        assertThat(area).isNotNull();
        assertThat(area.getId()).isEqualTo(1);
    }

    @Test
    public void getRolesTest() {
        final List<Area> votes = areaRepository.findAll();
        assertThat(votes).isNotNull();
    }
}
