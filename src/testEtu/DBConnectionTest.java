package testEtu;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.Test;
import serie.DBConnection;

public class DBConnectionTest {

    @Test
    public void testConnection() throws SQLException {
        // Vérification qu'il n'existe qu'un seul objet
        // connexion vers la base même si la connexion est demandée à plusieurs
        // reprises.
        Connection con1 = DBConnection.getConnection();
        Connection con2 = DBConnection.getConnection();
        assertEquals("Devrait etre la meme reference", true, con1.toString().equals(con2.toString()));
        assertEquals("Devrait etre de type java.sql.Connection", true, con1 instanceof java.sql.Connection);
        // Test changement de bdd
        DBConnection.setNomDB("testunicite");
        con1 = DBConnection.getConnection();
        // creation de la table test
        {
            String createString = "CREATE TABLE Test ( " + "ID INTEGER  AUTO_INCREMENT, " + "PRIMARY KEY (ID))";
            Statement stmt = con1.createStatement();
            stmt.executeUpdate(createString);
            System.out.println("1) creation table Test\n");
        }
    }

}