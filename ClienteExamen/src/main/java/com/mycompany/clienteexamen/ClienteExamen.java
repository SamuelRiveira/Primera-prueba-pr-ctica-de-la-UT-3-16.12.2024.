package com.mycompany.clienteexamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteExamen {
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
                
            System.out.println("Mandar IP: 192.168.1.8/24 y 192.168.1.10/24");
            String ip1 = "192.168.1.8/24";
            String ip2 = "192.168.1.10/24";
            String ipcomparar = ip1 + "$" + ip2;
            String ipcierre = "0.0.0.0/0";
            output.println(ipcomparar);

            String answer = input.readLine();
            System.out.println("La respuesta del servidor es: " + answer);
            
            output.println(ipcierre);
            
            String answer2 = input.readLine();
            System.out.println("La respuesta del servidor es: " + answer2);
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}