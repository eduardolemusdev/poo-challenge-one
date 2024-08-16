package com.udb.services;

import com.udb.database.DatabaseUDB;
import com.udb.models.Student;
import com.udb.utils.Console;

import java.util.Scanner;

public class AddStudentService implements IService {
    private final DatabaseUDB databaseUDB;
    public AddStudentService(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
    }

    @Override
    public void executeService() {
        try{
            String universityClass = Console.ReadString("Materia a cursar: ");
            String carnet = Console.ReadString("Ingresa el Carnet del estudiante: ");
            String name = Console.ReadString("Ingresa el Nombre del estudiante: ");

            Student student = new Student(carnet, name);
            databaseUDB.addNewStudent(universityClass, student);

            Console.WriteString("Estudiante agregado correctamente!", true);
        }catch (Exception e){
            Console.WriteString(e.getMessage(),true);
        }
    }
}
