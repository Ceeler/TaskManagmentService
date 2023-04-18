package com.example.taskmanagmentservice.rest.User;


import com.example.taskmanagmentservice.database.enums.Role;
import com.example.taskmanagmentservice.database.structure.User.User;
import com.example.taskmanagmentservice.database.structure.User.UserRepository;
import com.example.taskmanagmentservice.model.request.UserData;
import com.example.taskmanagmentservice.model.response.AuthResponse;
import com.example.taskmanagmentservice.model.response.UserInfo;
import com.example.taskmanagmentservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserResponseBuilder {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity<AuthResponse> processRegistrationUser(UserData userData){
        User user = User.builder()
                .username(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .role(Role.USER)
                .build();
        //TODO Data validation
        userRepository.save(user);
        String jwtToken = jwtService.generateJwt(user);
        return new ResponseEntity<>(AuthResponse.builder().token(jwtToken).build(), HttpStatus.CREATED);

    }

    public ResponseEntity<AuthResponse> processAuthenticationUser(UserData userData){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userData.getUsername(),
                        userData.getPassword()
                )
        );

        User user = userRepository.findByUsername(userData.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateJwt(user);

        return new ResponseEntity<>(AuthResponse.builder().token(jwtToken).build(), HttpStatus.OK);
    }

    public ResponseEntity<UserInfo> processGetUserInfo(Long id){
        return new ResponseEntity<>(new UserInfo(),HttpStatus.OK );
    }

}
