/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.Usluga;
import logika.so.radnik.LoginSO;
import java.util.List;
import logika.so.korisnik.IzmeniKorisnikaSO;
import logika.so.korisnik.ObrisiKorisnikaSO;
import logika.so.korisnik.PretraziKorisnikeSO;
import logika.so.korisnik.SacuvajKorisnikaSO;
import logika.so.korisnik.VratiKorisnikeSO;
import logika.so.mesto.VratiMestaSO;
import logika.so.racun.SacuvajRacunSO;
import logika.so.racun.VratiPoslednjiRacunSO;
import logika.so.tarifa.VratiSveTarifeSO;
import logika.so.tarifa.VratiTarifePoUsluziSO;
import logika.so.ugovor.SacuvajUgovorSO;
import logika.so.ugovor.VratiPoslednjiUgovorSO;
import logika.so.usluga.VratiUslugeSO;
import logika.so.zaduzenje.VratiZaduzenjaSO;

/**
 *
 * @author cvetan
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> vratiMesta() throws RuntimeException{
        VratiMestaSO vm = new VratiMestaSO();
        vm.izvrsiOperaciju(new Mesto());
        return vm.getLista();
    }
    
    public void sacuvajKorisnika(Object obj) throws RuntimeException{
        SacuvajKorisnikaSO sk = new SacuvajKorisnikaSO();
        sk.izvrsiOperaciju(obj);
    }
    
    public void izmeniKorisnika(Object obj) throws RuntimeException {
        IzmeniKorisnikaSO ik = new IzmeniKorisnikaSO();
        ik.izvrsiOperaciju(obj);
    }
    
    public void obrisiKorisnika(Object obj) throws RuntimeException {
        ObrisiKorisnikaSO ok = new ObrisiKorisnikaSO();
        ok.izvrsiOperaciju(obj);
    }
    
    public List<OpstiDomenskiObjekat> pretraziKorisnike(Object obj) throws RuntimeException {
        PretraziKorisnikeSO pk = new PretraziKorisnikeSO();
        pk.izvrsiOperaciju(obj);
        return pk.getLista();
    }
    
    public List<OpstiDomenskiObjekat> vratiUsluge() {
        VratiUslugeSO vu = new VratiUslugeSO();
        vu.izvrsiOperaciju(new Usluga());
        return vu.getLista();
    }
    
    public List<OpstiDomenskiObjekat> vratiTarifePoUsluzi(Object obj) {
        VratiTarifePoUsluziSO vt = new VratiTarifePoUsluziSO();
        vt.izvrsiOperaciju(obj);
        return vt.getLista();
    }
    
    public List<OpstiDomenskiObjekat> vratiSveTarife(Object obj) {
        VratiSveTarifeSO vst = new VratiSveTarifeSO();
        vst.izvrsiOperaciju(obj);
        return vst.getLista();
    }
    
    public List<OpstiDomenskiObjekat> login(Object obj) {
        LoginSO login = new LoginSO();
        login.izvrsiOperaciju(obj);
        return login.getLista();
    }
    
    public void sacuvajUgovor(Object obj) {
        SacuvajUgovorSO su = new SacuvajUgovorSO();
        su.izvrsiOperaciju(obj);
    }
    
    public OpstiDomenskiObjekat vratiPoslednjiUgovor(Object obj) {
        VratiPoslednjiUgovorSO vpu = new VratiPoslednjiUgovorSO();
        vpu.izvrsiOperaciju(obj);
        return vpu.getUgovor();
    }
    
    public List<OpstiDomenskiObjekat> vratiKorisnike(Object obj) {
        VratiKorisnikeSO vk = new VratiKorisnikeSO();
        vk.izvrsiOperaciju(obj);
        return vk.getLista();
    }
    
    public List<OpstiDomenskiObjekat> vratiZaduzenja(Object obj) {
        VratiZaduzenjaSO vzd = new VratiZaduzenjaSO();
        vzd.izvrsiOperaciju(obj);
        return vzd.getLista();
    }
    
    public OpstiDomenskiObjekat vratiPoslednjiRacun(Object obj) {
        VratiPoslednjiRacunSO vpr = new VratiPoslednjiRacunSO();
        vpr.izvrsiOperaciju(obj);
        return vpr.getRacun();
    }
    public void sacuvajRacun(Object obj) {
        SacuvajRacunSO sr = new SacuvajRacunSO();
        sr.izvrsiOperaciju(obj);
    }
}
