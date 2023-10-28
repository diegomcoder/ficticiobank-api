package com.diegomd.ficticiobankapi.models;

import com.diegomd.ficticiobank.layouts.components.Screen;
import com.diegomd.ficticiobank.models.request.Request;
import com.diegomd.ficticiobank.models.response.Response;
import com.google.gson.Gson;

import java.util.UUID;

public class ATMModel {
    private String context;
    private final UUID atmId;
    Gson gson = new Gson();
    public ATMModel(UUID atmId) {
        this.atmId = atmId;
    }

    public void handleActionCommand(Screen screen, int actionCommand) {

        if (context == null) {
            if (actionCommand !=8) return;

            Request createSectionRq = new Request().toCreateNewSection(atmId);
            String jsonResp = Server.importRequest(gson.toJson(createSectionRq));
            Response respObj = gson.fromJson(jsonResp, Response.class);
            context = respObj.currentContext;
            screen.redesign("src/main/assets/atm-main-menu.png");
        }

        if (context.equals("newSection") && actionCommand == 7) {
            screen.redesign("src/main/assets/I-01.png");
        }
    }
}
