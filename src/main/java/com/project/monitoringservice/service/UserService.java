package com.project.monitoringservice.service;

import com.project.monitoringservice.model.User;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);
}
