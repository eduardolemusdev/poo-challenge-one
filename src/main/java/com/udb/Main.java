package com.udb;

import com.udb.controllers.MenuController;
import com.udb.database.DatabaseUDB;

public class Main {
    public static void main(String[] args) {
        DatabaseUDB db = new DatabaseUDB();
        MenuController menu = new MenuController(db);
        menu.showMenu();
    }
}