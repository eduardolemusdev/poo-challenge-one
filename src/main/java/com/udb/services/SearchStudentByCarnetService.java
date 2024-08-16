package com.udb.services;

import com.udb.database.DatabaseUDB;
import com.udb.exceptions.StudentNotFoundException;
import com.udb.utils.Console;

import java.util.Scanner;

public class SearchStudentByCarnetService implements IService{
    private final DatabaseUDB databaseUDB;
    public SearchStudentByCarnetService(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
    }

    @Override
    public void executeService() {
        try{
            Scanner sc = new Scanner(System.in);

            String universityClass = Console.ReadString("Ingrese la materia en que esta inscrita el estudiante: ");
            String carnet = Console.ReadString("Carnet del estudiante: ");

            this.databaseUDB.searchStudentByCarnet(universityClass, carnet);
        }catch (StudentNotFoundException e){
            Console.WriteString(e.getMessage(),true);
        }
    }
}
