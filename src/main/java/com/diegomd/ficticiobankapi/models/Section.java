package com.diegomd.ficticiobankapi.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Section {
    public UUID sectionId;
    public String sectionOpenedAt;
    public UUID atmClientId;
    public String currentContext;
    public String lastRequest;

    Section(UUID atmClientId, String currentContext) {
        this.sectionId = UUID.randomUUID();
        this.atmClientId = atmClientId;
        this.currentContext = currentContext;
        this.sectionOpenedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.lastRequest = this.sectionOpenedAt;
    }

    public String getSectionOpenedAt() {
        return this.sectionOpenedAt;
    }

    public UUID getSectionId() {
        return sectionId;
    }

    public UUID getAtmClientId() {
        return atmClientId;
    }

    public String getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(String currentContext) {
        this.currentContext = currentContext;
    }

    public String getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(String lastRequest) {
        this.lastRequest = lastRequest;
    }
}
