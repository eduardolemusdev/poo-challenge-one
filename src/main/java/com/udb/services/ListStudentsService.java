package com.udb.services;

import com.udb.database.DatabaseUDB;
import com.udb.exceptions.UniversityClassNotFoundException;
import com.udb.models.Student;
import com.udb.utils.Console;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ListStudentsService implements IService{
    private final DatabaseUDB databaseUDB;

    public ListStudentsService(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
    }

    @Override
    public void executeService() {
        try{

            String universityClass = Console.ReadString("Ingresa el nombre de la clase/materia: ");

            Map<String, Student> students = this.databaseUDB.getStudentsByClass(universityClass);

            for (Student student : students.values()) {
                Console.WriteString("Carnet: " + student.getCarnet() + ", Nombre: " + student.getName(),true);
            }
        }catch (UniversityClassNotFoundException e){
            Console.WriteString(e.getMessage(), true);
        }
    }
}
