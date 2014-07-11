/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author cvetan
 */
public class DatabaseBroker {

    private Connection konekcija;
    private static DatabaseBroker instanca;

    private DatabaseBroker() {
    }

    public static DatabaseBroker vratiInstancu() {
        if (instanca == null) {
            instanca = new DatabaseBroker();
        }
        return instanca;
    }

    public void ucitajDrajver() throws RuntimeException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Nemoguće učitavanje drajvera!");
        }
    }

    public void otvoriKonekciju() throws RuntimeException {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost/tk_operater?user=root&password=srbija1918&useUnicode=true&characterEncoding=UTF-8");
            konekcija.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguće otvaranje konekcije!");
        }
    }

    public void zatvoriKonekciju() throws RuntimeException {
        try {
            konekcija.close();
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguće zatvaranje konekcije");
        }
    }

    public void commitTransakcije() throws RuntimeException {
        try {
            konekcija.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguća potvrda transakcije! \n Operacija nije izvršena!");
        }
    }

    public void rollbackTransakcije() throws RuntimeException {
        try {
            konekcija.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguć rollback transakcije!");
        }
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele() + "(" + odo.vratiParametreZaInsert() + ") VALUES(" + odo.vratiVrednostiZaInsert() + ")";
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Objekat nije sačuvan!");
        }
    }

    public void izmeni(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostiZaUpdate() + " WHERE " + odo.vratiPrimarniKljuc();
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Objekat nije izmenjen!");
        }
    }

    public void obrisi(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiPrimarniKljuc();
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Objekat nije obrisan!");
        }
    }

    public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "SELECT " + odo.vratiParametreZaSelect() + " FROM " + odo.vratiNazivTabele() + " " + odo.vratiUslovSpajanja() + " ORDER BY " + odo.vratiUslovSortiranja();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            return odo.vratiListuObjekata(rs);
        } catch (Exception e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Lista nije vraćena!");
        }
    }

    public List<OpstiDomenskiObjekat> vratiListuPoUslovu(OpstiDomenskiObjekat odo, String uslov) throws RuntimeException {
        try {
            String upit = "SELECT " + odo.vratiParametreZaSelect() + " FROM " + odo.vratiNazivTabele() + " " + odo.vratiUslovSpajanja() + " WHERE " + uslov + 
                    " ORDER BY " + odo.vratiUslovSortiranja();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            return odo.vratiListuObjekata(rs);
        } catch (Exception e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Provera nije izvršena!");
        }
    }

    public List<OpstiDomenskiObjekat> izvrsiPretragu(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "SELECT " + odo.vratiParametreZaSelect() + " FROM " + odo.vratiNazivTabele() + " " + odo.vratiUslovSpajanja() + " WHERE " + odo.vratiUslovPretrage()
                    + " ORDER BY " + odo.vratiUslovSortiranja();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            return odo.vratiListuObjekata(rs);
        } catch (Exception e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Pretraga nije izvršena!");
        }
    }

    public OpstiDomenskiObjekat vratiPoslednjiElement(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String upit = "SELECT " + odo.vratiParametreZaSelect() + " FROM " +  odo.vratiNazivTabele() + " " + odo.vratiUslovSpajanja() + " WHERE " + odo.vratiUslovZaPoslednjiElement() + " ORDER BY "
                    + odo.vratiUslovSortiranja() + " LIMIT 1";
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            return odo.vratiObjekat(rs);
        } catch (Exception e) {
            throw new RuntimeException("Nemoguće izvršenje upita! \n Objekat nije vracen!!");
        }
    }

}
