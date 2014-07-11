/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import niti.KlijentskaNit;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cvetan
 */
public class Komunikacija {

    private int brojPorta;
    private Socket socket;
    private boolean kraj = false;

    public void pokreniServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("Server je pokrenut.");
        while (!kraj) {
            socket = ss.accept();
            System.out.println("Klijent se povezao.");
            KlijentskaNit nit = new KlijentskaNit(socket);
            nit.start();
        }
    }

    public void zaustaviServer() {
        kraj = true;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Komunikacija k = new Komunikacija();
        k.pokreniServer();
    }
}
