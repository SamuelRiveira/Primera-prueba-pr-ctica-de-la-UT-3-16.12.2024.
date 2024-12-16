package com.mycompany.primera_prueba_practica_de_la_ut_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        
        // IP y puerto del servidor
        String host = "localhost";
        int port = 12346;
        
        try {
            //Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            boolean activo = false;
            
            while (activo == false) {
                
                System.out.println("Mandar IP: 172.168.6.0/24");
                String ip1 = "172.168.6.0/24";
                output.println(ip1);

                String answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer);
                
                /*
                if (answer.contains("Acertaste")) {
                    activo = true;
                    System.out.println("Ganaste");
                    break;
                } else if (answer.contains("mayor")) {
                    LimiteInferior = numero;
                } else if (answer.contains("menor")) {
                    LimiteSuperior = numero;
                }
                */
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
