package com.mycompany.primera_prueba_practica_de_la_ut_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Primera_prueba_practica_de_la_UT_3 {

    public static void main(String[] args) {
        int port = 12346;
        
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port + ".");
            
            while (true) {
                try (Socket client = server.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: " + client.getInetAddress());

                    String message = input.readLine();
                    System.out.println("Mensaje recibido: " + message);
                    
                    if(message.equals("0.0.0.0/0")){
                        System.out.println("Ha llegado la dirección 0.0.0.0/0, finaliza el programa");
                        output.println("Ha llegado la dirección 0.0.0.0/0, finaliza el programa");
                        break;
                    }
                    
                    String[] ips = message.split("\\$");
                    String ip1 = ips[0];
                    String ip2 = ips[1];

                    // Crear objetos de la clase IPv4Address
                    IPv4Address dir1 = new IPv4Address(ip1.split("/")[0], ip1.split("/")[1]);
                    IPv4Address dir2 = new IPv4Address(ip2.split("/")[0], ip2.split("/")[1]);

                    // Responder al cliente
                    String comprobacion = String.format("Red IP1: %s, %s\nRed IP2: %s, %s",
                            dir1.getBinaryNetwork(), dir1.getDecimalNetwork(),
                            dir2.getBinaryNetwork(), dir2.getDecimalNetwork());
                    
                    System.out.println(comprobacion);
                    
                    String respuesta = "";
                    
                    if((dir1.getDecimalNetwork()).equals(dir2.getDecimalNetwork())){
                        respuesta = "Están en la misma red";
                    }else{
                        respuesta = "No están en la misma red";
                    }
                    
                    System.out.println(respuesta);
                    output.println(respuesta);
                    System.out.println("Respuesta enviada al cliente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}