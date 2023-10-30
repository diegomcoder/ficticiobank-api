package com.diegomd.ficticiobankapi;

import com.diegomd.ficticiobankapi.entities.section.SectionController;
import com.diegomd.ficticiobankapi.entities.section.SectionModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @CrossOrigin
    @GetMapping("/new-section")
    public ResponseEntity test(HttpServletRequest request, HttpServletResponse response) {

        var atmId = request.getHeader("atm-id");

        SectionModel section = SectionController.findByAtmClientId(UUID.fromString(atmId));

        if (section == null) {
            var savedSection = SectionController.save(new SectionModel(UUID.fromString(atmId),"new-section"));
            return ResponseEntity.status(HttpStatus.OK).body(savedSection);
        }else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(section);
        }

    }
}
