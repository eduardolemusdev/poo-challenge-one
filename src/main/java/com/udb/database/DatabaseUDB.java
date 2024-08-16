package com.udb.database;

import com.udb.exceptions.StudentNotFoundException;
import com.udb.exceptions.UniversityClassNotFoundException;
import com.udb.models.Student;
import com.udb.utils.Console;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class DatabaseUDB {
    private final Map<String, List<Student>> UniversityClassStudents = new HashMap<>();

    public DatabaseUDB() {
        UniversityClassStudents.put("POO", new ArrayList<>());
    }

    public List<Student> getStudentsByClass(String universityClass) throws UniversityClassNotFoundException {
        return this.getStudentsClassIfExistOrThrowsException(universityClass);
    }

    public void addNewStudent(String universityClass, Student newStudent) throws UniversityClassNotFoundException {
        List<Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);
        students.add(newStudent);
    }

    public void removeStudent(String universityClass, String carnet) throws StudentNotFoundException {
        try {
            List<Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);
            boolean studentExist = students.stream().anyMatch(student -> student.getCarnet().equals(carnet));

            StringBuffer sb = new StringBuffer();
            String message = sb.append("El estudiante con el carnet: ").append(carnet).append(", no existe.").toString();

            if (!studentExist) throw new StudentNotFoundException(message);

            List<Student> updatedStudents = students.stream().filter(student -> !student.getCarnet().equals(carnet)).collect(toList());
            this.UniversityClassStudents.put(universityClass, updatedStudents);

            Console.WriteString("Estudiante eliminado.",true);
        }catch (UniversityClassNotFoundException e){
            Console.WriteString(e.getMessage(), true);
        }
    }

    public void searchStudentByCarnet(String universityClass, String carnet) throws StudentNotFoundException {
        try {
            List<Student> students = this.getStudentsClassIfExistOrThrowsException(universityClass);
            boolean studentExist = students.stream().anyMatch(student -> student.getCarnet().equals(carnet));
            if (!studentExist) throw new StudentNotFoundException("Alumno no encontrado, no se\n" +
                    "puede Mostrar.");

            Student targetStudent = students.stream().filter(student -> student.getCarnet().equals(carnet)).findFirst().get();
            StringBuffer sb = new StringBuffer();
            String message = sb.append("Busqueda, Estudiante: ").append(targetStudent.getCarnet()).append(" - ").append(targetStudent.getName()).toString();
            Console.WriteString(message,true);

        }catch (UniversityClassNotFoundException e){
            Console.WriteString(e.getMessage(),true);
        }
    }

    public void addNewUniversityClass(String universityClass) {
        boolean universityClassExists = UniversityClassStudents.containsKey(universityClass);
        StringBuffer sb = new StringBuffer();
        String message = sb.append("La materia: ").append(universityClass).append(", ya existe.").toString();

        if (universityClassExists) {
            Console.WriteString(message,true);
            return;
        }

        UniversityClassStudents.put(universityClass, new ArrayList<>());
        Console.WriteString("Materia registrada correctamente.", true);
    }
    private List<Student> getStudentsClassIfExistOrThrowsException(String universityClass) throws UniversityClassNotFoundException {
        StringBuffer sb = new StringBuffer();
        String message = sb.append("La materia: ").append(universityClass).append(", no existe.").toString();

        return Optional.ofNullable(this.UniversityClassStudents.get(universityClass))
                .orElseThrow(() -> new UniversityClassNotFoundException(message));
    }
}
