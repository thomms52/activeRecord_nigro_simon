package activeRecord;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class Personnage {

    private String nom;
    private int id;
    private static int id_serie;

    public Personnage(String nom, Serie s) {
        this.nom = nom;
        int idS = -1;
        if (s != null) {
            idS = s.getId();
        }
        this.id_serie = idS;
        this.id = -1;
    }

    private Personnage(String nom, int id, int idS) {
        this.nom = nom;
        this.id = id;
        this.id_serie=idS;
    }

    public static Personnage findById(int id) throws SQLException {
        Personnage p = null;
        String SQLPrep = "SELECT * FROM Personnage WHERE id = ?";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.setInt(1, id);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        if (rs.next()) {
            String nom = rs.getString("nom");
            int idS = rs.getInt("id_serie");
            int idP = rs.getInt("id");
            Serie s = Serie.findById(idS);
            p = new Personnage(nom, s);
            p.setId(idP);
        }
        return p;
    }

    public static void createTable() throws SQLException {
        String createString = "CREATE TABLE Personnage ( " + "ID INTEGER  AUTO_INCREMENT, " + "NOM varchar(40) NOT NULL, "
                + "ID_SERIE INT(40) NOT NULL, " + "PRIMARY KEY (ID), KEY ID_SERIE (ID_SERIE))";
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate(createString);
    }

    public static void deleteTable() throws SQLException {
        String drop = "DROP TABLE Personnage";
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate(drop);
    }

    public void save() throws Exception {
        if (this.id == -1) {
            this.saveNew();
        } else {
            this.update();
        }
    }

    private void saveNew() throws Exception {
        if(this.getSerie() == null) {
            throw new Exception();
        }
        String SQLPrep = "INSERT INTO Personnage (nom, id_serie) VALUES (?,?)";
        PreparedStatement prep;
        prep = DBConnection.getConnection().prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.nom);
        prep.setInt(2, this.id_serie);
        prep.executeUpdate();
    }

    public void delete() throws SQLException {
        PreparedStatement prep = DBConnection.getConnection().prepareStatement("DELETE FROM Personnage WHERE id = ?");
        prep.setInt(1, this.id);
        prep.execute();
        this.id = -1;
    }

    private void update() throws Exception {
        if(this.getSerie() == null) {
            throw new Exception();
        }
        String SQLprep = "update Personnage set nom = ?, id_serie = ? where id = ?";
        PreparedStatement prep = DBConnection.getConnection().prepareStatement(SQLprep);
        prep.setString(1, this.nom);
        prep.setInt(2, this.id_serie);
        prep.setInt(3, this.id);
        prep.execute();
    }

    public static Serie getSerie() throws SQLException {
        return Serie.findById(id_serie);
    }

    public static ArrayList<Personnage> findBySerie(Serie s) throws SQLException {
        ArrayList<Personnage> liste = new ArrayList<Personnage>();
        String SQLPrep = "SELECT * FROM Personnage WHERE id_serie = ?";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.setInt(1, s.getId());
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        while(rs.next()) {
            String nom = rs.getString("nom");
            int id_serie = rs.getInt("id_serie");
            int idP = rs.getInt("id");
            Personnage p = new Personnage(nom, s);
            p.setId(idP);
            liste.add(p);
        }
        return liste;
    }

    public static ArrayList<Personnage> findByGenre(String genre) throws SQLException {
        ArrayList<Personnage> liste = new ArrayList<Personnage>();
        String SQLPrep = "SELECT * FROM Personnage INNER JOIN Serie ON Serie.id = Personnage.id_serie WHERE genre = ?;";
        PreparedStatement prep1 = DBConnection.getConnection().prepareStatement(SQLPrep);
        prep1.setString(1, genre);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        while (rs.next()) {
            String nom = rs.getString("nom");
            int id = rs.getInt("id");
            int id_serie = rs.getInt("id_serie");
            Personnage p = new Personnage(nom, id ,id_serie);
            liste.add(p);
        }
        return liste;
    }

    public String toString() {
        return "(" + this.id + ") " + this.nom + " " + this.id_serie;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String n) {
        this.nom = n;
    }

    public int getIdSerie() {
        return this.id_serie;
    }

    public void setIdSerie(int i) {
        this.id_serie = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

}
