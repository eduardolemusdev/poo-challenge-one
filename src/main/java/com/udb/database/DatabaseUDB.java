package com.udb.database;

import com.udb.exceptions.StudentAlreadyExists;
import com.udb.exceptions.StudentNotFoundException;
import com.udb.exceptions.UniversityClassNotFoundException;
import com.udb.models.Student;
import com.udb.utils.Console;

import java.util.*;


public class DatabaseUDB {

    private final Map<String, Map<String, Student>> UniversityStudents = new HashMap<>();

    public DatabaseUDB() {
        UniversityStudents.put("POO", new HashMap<>());
    }

    public Map<String, Student> getStudentsByClass(String universityClass) throws UniversityClassNotFoundException {
        return this.getStudentsClassIfExistOrThrowsException(universityClass);
    }

    public void addNewStudent(String universityClass, Student newStudent) throws UniversityClassNotFoundException, StudentAlreadyExists {
        Map<String, Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);

        boolean studentAlreadyExist = students.containsKey(newStudent.getCarnet());
        StringBuffer sb = new StringBuffer();
        String errorMessage = sb.append("El carnet: ").append(newStudent.getCarnet()).append(", ya existe.").toString();

        if (studentAlreadyExist) {
            throw new StudentAlreadyExists(errorMessage);
        }
        students.put(newStudent.getCarnet(), newStudent);

    }

    public void removeStudent(String universityClass, String carnet) throws StudentNotFoundException {
        try {
            Map<String, Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);
            boolean studentExist = students.containsKey(carnet);

            StringBuffer sb = new StringBuffer();
            String message = sb.append("El estudiante con el carnet: ").append(carnet).append(", no existe.").toString();

            if (!studentExist) throw new StudentNotFoundException(message);

            students.remove(carnet);

            Console.WriteString("Estudiante eliminado.",true);
        }catch (UniversityClassNotFoundException e){
            Console.WriteString(e.getMessage(), true);
        }
    }

    public void searchStudentByCarnet(String universityClass, String carnet) throws StudentNotFoundException {
        try {
            Map<String, Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);
            boolean studentExist = students.containsKey(carnet);

            if (!studentExist) throw new StudentNotFoundException("Alumno no encontrado, no se\n" +
                    "puede Mostrar.");

            Student targetStudent = students.get(carnet);

            StringBuffer sb = new StringBuffer();
            String message = sb.append("Busqueda, Estudiante: ").append(targetStudent.getCarnet()).append(" - ").append(targetStudent.getName()).toString();

            Console.WriteString(message,true);

        }catch (UniversityClassNotFoundException e){
            Console.WriteString(e.getMessage(),true);
        }
    }

    public void addNewUniversityClass(String universityClass) {
        boolean universityClassExists = this.UniversityStudents.containsKey(universityClass);
        StringBuffer sb = new StringBuffer();
        String message = sb.append("La materia: ").append(universityClass).append(", ya existe.").toString();

        if (universityClassExists) {
            Console.WriteString(message,true);
            return;
        }

        UniversityStudents.put(universityClass, new HashMap<>());
        Console.WriteString("Materia registrada correctamente.", true);
    }
    private Map<String, Student> getStudentsClassIfExistOrThrowsException(String universityClass) throws UniversityClassNotFoundException {
        StringBuffer sb = new StringBuffer();
        String message = sb.append("La materia: ").append(universityClass).append(", no existe.").toString();


        return Optional.ofNullable(this.UniversityStudents.get(universityClass))
                .orElseThrow(() -> new UniversityClassNotFoundException(message));
    }
}
