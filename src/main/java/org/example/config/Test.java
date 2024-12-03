package org.example.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("thiago3435"); // Reemplaza "12345" con la contraseña
        System.out.println("Contraseña cifrada: " + hashedPassword);
    }
    /*UPDATE usuario SET contrasena = '$2a$10$6SftNaVAKPmsDUIkX8Tq.uMvJVDk1wP2liqC6kZlIlZ5BzzGHPma6' WHERE correo_electronico = 'juan.perez@gmail.com';*/
    /*UPDATE usuario SET contrasena = '$2a$10$KQtzuoAnpqg6wQI.pu3lZePiZ.5V/qG8K3wVZ7zLOpkozHjOKBihW' WHERE correo_electronico = 'Santi@gmail.com';*/
}

