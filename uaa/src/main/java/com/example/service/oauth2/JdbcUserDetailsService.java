package com.example.service.oauth2;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JdbcUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JdbcUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRoles())
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().flatMap(
                role -> role.getAuthorities().stream().map(
                        authority -> new SimpleGrantedAuthority(authority.getAuthority())
                )
        ).collect(Collectors.toList());
    }
}
