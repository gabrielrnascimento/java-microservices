package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.EmailProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final EmailProducer emailProducer;

    public UserService(UserRepository userRepository, EmailProducer emailProducer) {
        this.userRepository = userRepository;
        this.emailProducer = emailProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = userRepository.save(userModel);
        emailProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
