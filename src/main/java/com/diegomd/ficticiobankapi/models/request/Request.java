package com.diegomd.ficticiobankapi.models.request;

import java.util.UUID;

public class Request {
    public UUID atmClientId;
    public UUID sectionId;
    public String currentContext;
    public String context;
    public Attachments attachments;

    public Request toCreateNewSection(UUID atmClientId) {
        this.context = "newSection";
        this.atmClientId = atmClientId;
        return this;
    }
}
