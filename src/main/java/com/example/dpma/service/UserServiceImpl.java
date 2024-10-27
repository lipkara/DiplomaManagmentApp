package com.example.dpma.service;

import java.util.Optional;

import com.example.dpma.dao.UserDAO;
import com.example.dpma.model.Professor;
import com.example.dpma.model.Role;
import com.example.dpma.model.Student;
import com.example.dpma.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    StudentService studentService;

    @Autowired
    ProfessorService professorService;

    @Override
    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.save(user);

        if (user.getRole().equals(Role.STUDENT)) {
            Student student = new Student();
            student.setUser(user);
            studentService.saveProfile(student);
        } else if (user.getRole().equals(Role.PROFESSOR)) {
            Professor professor = new Professor();
            professor.setUser(user);
            professorService.saveProfile(professor);
        }

    }

    @Override
    public boolean isUserPresent(User user) {
        Optional<User> storedUser = userDAO.findByUsername(user.getUsername());
        return storedUser.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", username)
                ));
    }

    @Override
    public User loadUserByName(String name) {
        return userDAO.findUserByUsername(name);
    }
}