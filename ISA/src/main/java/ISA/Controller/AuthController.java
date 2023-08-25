package ISA.Controller;

import java.util.*;
import java.util.stream.Collectors;

import ISA.Model.DTO.Activate;
import ISA.Model.DTO.LoginRequest;
import ISA.Model.DTO.SingupRequest;
import ISA.Model.User;
import ISA.Model.response.JwtResponse;
import ISA.Repository.UserRepository;
import ISA.Service.UserService;
import ISA.security.JwtUtils;



import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService IUserService;
    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Optional<String> role = roles.stream().findFirst();
        User user= userRepository.findByEmail(loginRequest.getUsername());
        if(user.isActivated()) {
            JwtResponse response = new JwtResponse(jwt, userDetails.getUsername(), role);
            return ResponseEntity
                    .ok(response);
        }
        else{
            return ResponseEntity.badRequest().body("Nalog nije aktiviran");
        }
    }

    @PostMapping("/activateProfile")
    public ResponseEntity<?> activateUser(@Valid @RequestBody Activate act) {
        Optional<User> korisnik=userRepository.findById(act.getId());
        if(korisnik==null){
            return ResponseEntity.badRequest().body("Error: Aktivacioni link nije ispravan!");
        }
        korisnik.get().setActivated(true);
        userRepository.save(korisnik.get());
        return  ResponseEntity.ok().body("Akivacija profila uspesna!");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User(signUpRequest);
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        User kk= IUserService.RegisterKorisnik(user);

        return ResponseEntity.ok("User registered successfully, email for validation is sent!");
    }

}