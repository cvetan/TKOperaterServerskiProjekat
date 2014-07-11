/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.so.zaduzenje;

import baza.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import domen.Zaduzenje;
import java.util.List;
import logika.so.OpstaSO;

/**
 *
 * @author cvetan
 */
public class VratiZaduzenjaSO extends OpstaSO {

    private List<OpstiDomenskiObjekat> lista;

    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public void setLista(List<OpstiDomenskiObjekat> lista) {
        this.lista = lista;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws RuntimeException {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
        Zaduzenje z = (Zaduzenje) obj;
        lista = DatabaseBroker.vratiInstancu().izvrsiPretragu(z);
    }

}
