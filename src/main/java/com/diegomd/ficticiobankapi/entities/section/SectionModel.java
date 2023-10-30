package com.diegomd.ficticiobankapi.entities.section;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@ToString
public class SectionModel {
    public UUID sectionId;
    public String sectionOpenedAt;
    public UUID atmClientId;
    public String currentContext;
    public String lastRequest;

    public SectionModel(UUID atmClientId, String currentContext) {
        this.sectionId = UUID.randomUUID();
        this.atmClientId = atmClientId;
        this.currentContext = currentContext;
        this.sectionOpenedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastRequest = this.sectionOpenedAt;
    }

    public void setCurrentContext(String currentContext) {
        this.currentContext = currentContext;
    }

    public void setLastRequest(String lastRequest) {
        this.lastRequest = lastRequest;
    }
}
