package ru.lilmoon.seminar3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.repository.ReaderRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ReaderRepository repository;

    public CustomUserDetailsService(ReaderRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ReaderEntity reader = repository.findByName(username);
        if (reader == null) throw new UsernameNotFoundException("Unknown user:" + username);
        return User.builder()
                .username(reader.getName())
                .password(reader.getPassword())
                .roles(String.valueOf(reader.getRole()))
                .build();
    }
}
