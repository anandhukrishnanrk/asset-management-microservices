package com.asset.asset_api.service;

import com.asset.asset_api.entity.UserSetup;
import com.asset.asset_api.repository.UserSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserSetupRepository userSetupRepository;

    public Optional<UserSetup> authenticate(String username, String password) {

        Optional<UserSetup> userRes = userSetupRepository.findByUserName(username);

        return userRes.filter(u ->
                u.getPassword() != null &&
                u.getPassword().equals(password)
        );
    }
}
