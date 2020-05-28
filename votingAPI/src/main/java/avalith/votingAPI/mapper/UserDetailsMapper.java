package avalith.votingAPI.mapper;

import avalith.votingAPI.model.Role;
import avalith.votingAPI.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

    /***
     * build() returns UserDetails object (name, password, authorities)
     * @param user
     * @return UserDetails object
     */
    public static UserDetails build(User user) {
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
    }

    /***
     * getAuthorities returns a set with all assigned roles for a User object
     * @param retrievedUser
     * @return Set<GrantedAuthority> object
     */
    private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
        Set<Role> roles = retrievedUser.getRoles();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;
    }
}
