package com.udb.services;

import com.udb.database.DatabaseUDB;
import com.udb.exceptions.StudentNotFoundException;
import com.udb.utils.Console;

import java.util.Scanner;

public class DeleteStudentService implements IService{
    private final DatabaseUDB databaseUDB;

    public DeleteStudentService(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
    }

    @Override
    public void executeService() {
        try {

            String universityClass = Console.ReadString("Ingrese la materia en que esta inscrita el estudiante: ");
            String carnet = Console.ReadString("Carnet del estudiante: ");

            this.databaseUDB.removeStudent(universityClass, carnet);
        }catch (StudentNotFoundException e){
            Console.WriteString(e.getMessage(),true);
        }
    }
}
