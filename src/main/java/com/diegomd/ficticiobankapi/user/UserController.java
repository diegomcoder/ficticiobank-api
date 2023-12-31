package com.diegomd.ficticiobankapi.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.diegomd.ficticiobankapi.entities.section.SectionController;
import com.diegomd.ficticiobankapi.entities.section.SectionModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
}
