package com.clinic.clinic.Service;

import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);

}
