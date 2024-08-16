package com.udb.services;

import com.udb.database.DatabaseUDB;
import com.udb.utils.Console;

import java.util.Scanner;

public class AddUniversityClassService implements IService{
    private final DatabaseUDB databaseUDB;
    public AddUniversityClassService(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
    }

    @Override
    public void executeService() {
        String universityClass = Console.ReadString("Ingrese el Nombre de la materia: ");

        this.databaseUDB.addNewUniversityClass(universityClass);
    }
}
