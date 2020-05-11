package com.example.autoshopserver.auth.registration;

import com.example.autoshopserver.auth.info.AuthInfo;
import com.example.autoshopserver.auth.info.AuthInfoMapper;
import com.example.autoshopserver.auth.rules.AuthRulesService;
import com.example.autoshopserver.user.Role;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthRulesService authRulesService;
    private final AuthInfoMapper authInfoMapper;

    @Transactional
    public String addUser(String username, String password) {
        AuthInfo authInfo;
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            authInfo = AuthInfo.builder()
                    .status("error")
                    .code(401)
                    .details("User exists")
                    .build();
            return authInfoMapper.toAuthInfoDTO(authInfo).getJson();
        }

        if (!authRulesService.checkLogin(username)) {
            authInfo = AuthInfo.builder()
                    .status("error")
                    .code(401)
                    .details("Login is too short")
                    .build();
            return authInfoMapper.toAuthInfoDTO(authInfo).getJson();
        }

        if (!authRulesService.checkPassword(password)) {
            authInfo = AuthInfo.builder()
                    .status("error")
                    .code(401)
                    .details("Password is too short")
                    .build();
            return authInfoMapper.toAuthInfoDTO(authInfo).getJson();
        }

        user.setActive(true);
        Set<Role> roles = new HashSet<Role>();
        roles.add(Role.USER);
        if (username.compareTo("admin") == 0 || username.compareTo("root") == 0) {
            roles.add(Role.ADMIN);
        }
        user.setRoles(roles);
        userRepository.save(user);

        authInfo = AuthInfo.builder()
                .status("ok")
                .code(200)
                .details("Success registration")
                .build();
        return authInfoMapper.toAuthInfoDTO(authInfo).getJson();
    }
}
