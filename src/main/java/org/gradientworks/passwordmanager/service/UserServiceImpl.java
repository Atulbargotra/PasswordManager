package org.gradientworks.passwordmanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gradientworks.passwordmanager.domain.Authority;
import org.gradientworks.passwordmanager.domain.User;
import org.gradientworks.passwordmanager.repo.AuthorityRepository;
import org.gradientworks.passwordmanager.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving User" + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching User " + username);
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getAuthority())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("No user found!!");
    }
}
