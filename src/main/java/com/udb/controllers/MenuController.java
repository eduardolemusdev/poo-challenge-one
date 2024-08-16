package com.udb.controllers;

import com.udb.database.DatabaseUDB;
import com.udb.services.*;
import com.udb.utils.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class MenuController {
    private final DatabaseUDB databaseUDB;
    private final Map<String, IService> servicesManager = new HashMap<>();

    public MenuController(DatabaseUDB databaseUDB) {
        this.databaseUDB = databaseUDB;
        this.initMenu();
    }

    public void showMenu(){
        String option;
        do {
            Scanner sc = new Scanner(System.in);
            Console.WriteString("1. Registrar estudiante", true);
            Console.WriteString("2. Buscar estudiante", true);
            Console.WriteString("3. Listar estudiantes", true);
            Console.WriteString("4. Eliminar estudiante", true);
            Console.WriteString("5. Registrar Nueva Materia", true);
            Console.WriteString("6. Salir", true);

            option = sc.nextLine();

            Optional<IService> serviceSelected = Optional.ofNullable(this.servicesManager.get(option));

            if(option.equals("6")){
                Console.WriteString("Apagando sistema...",true);
                System.exit(0);
            }

            if(serviceSelected.isPresent()){
                serviceSelected.get().executeService();
                this.waitForEnter(sc);
            }else{
                Console.WriteString("Opción no válida.", true);
                this.waitForEnter(sc);
            }

        }while (true);
    }
    private void initMenu(){
        IService addStudentService = new AddStudentService(this.databaseUDB);
        IService addUniversityClassService = new AddUniversityClassService(this.databaseUDB);
        IService deleteStudentService = new DeleteStudentService(this.databaseUDB);
        IService listStudentsService = new ListStudentsService(this.databaseUDB);
        IService searchStudentByCarnetService = new SearchStudentByCarnetService(this.databaseUDB);

        this.servicesManager.put("1", addStudentService);
        this.servicesManager.put("2", searchStudentByCarnetService);
        this.servicesManager.put("3", listStudentsService);
        this.servicesManager.put("4", deleteStudentService);
        this.servicesManager.put("5", addUniversityClassService);
    }
    private void waitForEnter(Scanner sc) {
        Console.WriteString("Presione Enter para continuar...",true);
        sc.nextLine();
    }
}
