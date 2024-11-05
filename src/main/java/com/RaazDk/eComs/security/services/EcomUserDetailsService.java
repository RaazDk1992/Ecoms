package com.RaazDk.eComs.security.services;

import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.repository.EcomUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EcomUserDetailsService implements UserDetailsService {

    @Autowired
    EcomUserRepository ecomUserRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EcomUser ecomUser = ecomUserRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username :"+username));

        return EcomUserDetails.build(ecomUser);
    }
}
