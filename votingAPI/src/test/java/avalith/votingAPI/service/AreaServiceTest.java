package avalith.votingAPI.service;

import avalith.votingAPI.model.Area;
import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import avalith.votingAPI.repository.AreaRepository;
import avalith.votingAPI.repository.RoleRepository;
import avalith.votingAPI.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AreaServiceTest {

    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaServiceImpl areaService;

    @Test
    public void getAreaUsingRepositoryTest() {
        areaService.getArea(1);

        verify(areaRepository).findById(1);
    }

    @Test
    public void getAreasUsingRepositoryTest() {
        areaService.getAreas();

        verify(areaRepository).findAll();
    }

    @Test
    public void saveAreaTest() {
        final Area areaToSave = Area.builder().name("Leader").build();

        Area newArea = Area.builder().name("Leader").build();

        when(areaRepository.save(newArea)).thenReturn(newArea);

        final Area savedArea = areaService.save(areaToSave);

        assertThat(savedArea).isEqualTo(newArea);
    }

}
