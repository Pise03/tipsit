package com;

import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try {
            System.out.println("1 SERVER partito in esecuzione ...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try {
            System.out.println("3 benvenuto nel client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
            
            stringaRicevuta = inDalClient.readLine();

            System.out.println("6 ricevuta la stringa dal cliente : " + stringaRicevuta);

            

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata + '\n');

            System.out.println("9 SERVER: fine elaborazione ... STACCA STACCA CI STANNO TRACCIANDO");
            client.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        server servente = new server();
        servente.attendi();
        servente.comunica();
    }
}
