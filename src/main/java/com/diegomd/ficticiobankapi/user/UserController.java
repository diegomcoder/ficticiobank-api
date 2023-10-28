package com.diegomd.ficticiobankapi.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/new-user")
    public ResponseEntity newUser(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByCpf(userModel.getCpf());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This user is already registered");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userRegistered = this.userRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body(userRegistered);
    }

    @CrossOrigin
    @GetMapping("/test")
    public String test() {
        return "Teste ok";
    }
}
