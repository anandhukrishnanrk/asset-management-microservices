package com.cis.api.service;

import com.cis.api.entity.Client;
import com.cis.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> authenticate(String username, String password) {
        // Simple plain text password check for demo purposes
        Optional<Client> clientRes = clientRepository.findByName(username);
        return clientRes
                .filter(client -> client.getPassword() != null && client.getPassword().equals(password));
    }

    // public User createUser(User user) {
    // return userRepository.save(user);
    // }
}
