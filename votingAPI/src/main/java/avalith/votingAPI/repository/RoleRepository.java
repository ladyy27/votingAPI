package avalith.votingAPI.repository;

import avalith.votingAPI.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    Role getRoleById(long i);
}
