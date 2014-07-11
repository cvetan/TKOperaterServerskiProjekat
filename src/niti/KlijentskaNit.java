/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package niti;

import domen.OpstiDomenskiObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import logika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

/**
 *
 * @author cvetan
 */
public class KlijentskaNit extends Thread{
    private Socket socket;
    private boolean kraj;

    public KlijentskaNit(Socket socket) {
        this.socket = socket;
        kraj = false;
    }

    @Override
    public void run() {
        try {
            obradiZahtevKlijenta();
        } catch (Exception e) {
        }
    }

    private void obradiZahtevKlijenta() throws IOException, ClassNotFoundException {
        while(!kraj) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toZahtev = (TransferObjekatZahtev) inSocket.readObject();
            TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
            try {
                switch(toZahtev.getOperacija()) {
                    case (Konstante.VRATI_MESTA):
                        List<OpstiDomenskiObjekat> lm = Kontroler.vratiInstancu().vratiMesta();
                        toOdgovor.setOdgovor(lm);
                        toOdgovor.setUspesan(true);
                        break;
                    case (Konstante.SACUVAJ_KORISNIKA):
                        Kontroler.vratiInstancu().sacuvajKorisnika(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setRezultat("Korisnik je sačuvan.");
                        break;
                    case (Konstante.PRETRAZI_KORISNIKE):
                        List<OpstiDomenskiObjekat> lk = Kontroler.vratiInstancu().pretraziKorisnike(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lk);
                        break;
                    case (Konstante.IZMENI_KORISNIKA):
                        Kontroler.vratiInstancu().izmeniKorisnika(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setRezultat("Podaci korisnika su izmenjeni.");
                        break;
                    case (Konstante.OBRISI_KORISNIKA):
                        Kontroler.vratiInstancu().obrisiKorisnika(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setRezultat("Korisnik je obrisan.");
                        break;
                    case (Konstante.VRATI_KORISNIKE):
                        List<OpstiDomenskiObjekat> lista = Kontroler.vratiInstancu().vratiKorisnike(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lista);
                        break;
                    case (Konstante.VRATI_USLUGE):
                        List<OpstiDomenskiObjekat> lu = Kontroler.vratiInstancu().vratiUsluge();
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lu);
                        break;
                    case (Konstante.VRATI_TARIFE_PO_USLUZI):
                        List<OpstiDomenskiObjekat> lt = Kontroler.vratiInstancu().vratiTarifePoUsluzi(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lt);
                        break;
                    case (Konstante.VRATI_SVE_TARIFE):
                        List<OpstiDomenskiObjekat> lst = Kontroler.vratiInstancu().vratiSveTarife(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lst);
                        break;
                    case (Konstante.LOGIN):
                        List<OpstiDomenskiObjekat> lr = Kontroler.vratiInstancu().login(toZahtev.getParametar());
                        if(lr.size() == 1) {
                            toOdgovor.setUspesan(true);
                        } else {
                            toOdgovor.setUspesan(false);
                            toOdgovor.setRezultat("Sistem ne može da nađe radnika sa tim podacima! \n Proverite unos!");
                        }
                        break;
                    case (Konstante.SACUVAJ_UGOVOR):
                        Kontroler.vratiInstancu().sacuvajUgovor(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setRezultat("Ugovor je sačuvan.");
                        break;
                    case (Konstante.VRATI_POSLEDNJI_UGOVOR):
                        OpstiDomenskiObjekat ugovor = Kontroler.vratiInstancu().vratiPoslednjiUgovor(toZahtev.getParametar());
                        toOdgovor.setOdgovor(ugovor);
                        toOdgovor.setUspesan(true);
                        break;
                    case (Konstante.VRATI_ZADUZENJA):
                        List<OpstiDomenskiObjekat> lz = Kontroler.vratiInstancu().vratiZaduzenja(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(lz);
                        break;
                    case (Konstante.VRATI_POSLEDNJI_RACUN):
                        OpstiDomenskiObjekat racun = Kontroler.vratiInstancu().vratiPoslednjiRacun(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setOdgovor(racun);
                        break;
                    case (Konstante.SACUVAJ_RACUN):
                        Kontroler.vratiInstancu().sacuvajRacun(toZahtev.getParametar());
                        toOdgovor.setUspesan(true);
                        toOdgovor.setRezultat("Račun je sačuvan.");
                        break;
                }
            } catch (RuntimeException e) {
                toOdgovor.setUspesan(false);
                toOdgovor.setRezultat(e.getMessage());
            }
            posaljiOdgovor(toOdgovor);
        }
    }

    private void posaljiOdgovor(TransferObjekatOdgovor toOdgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toOdgovor); 
    }
    
    
}
