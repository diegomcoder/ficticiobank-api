package com.diegomd.ficticiobankapi.database;

import com.diegomd.ficticiobankapi.entities.client.ClientModel;

import java.util.ArrayList;
import java.util.List;

public class USERS_DB {
    private static final List<ClientModel> users = new ArrayList<>();
    public static void save(ClientModel client) {
        users.add(client);
    }
}
