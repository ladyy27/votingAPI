package avalith.votingAPI.repository;

import avalith.votingAPI.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaRepository extends JpaRepository<Area,Long> {

    Area findByName(String name);

    Area findById(long id);

    List<Area> findAll();

}
