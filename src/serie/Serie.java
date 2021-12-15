package serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serie {

    private int id;
    private String nom;
    private String genre;

    public Serie(String nom, String genre) {
        this.id = -1;
        this.nom = nom;
        this.genre = genre;
    }

    public static List<Serie> findAll() throws SQLException {
        System.out.println("7.1) Recupere les series de la table serie.Serie");
        String SQLPrep = "SELECT * FROM Serie;";

        Connection connect = DBConnection.getInstance().getConnection();
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat

        List<Serie> liste = new ArrayList<Serie>();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String genre = rs.getString("genre");

            Serie s = new Serie(nom, genre);
            int id = rs.getInt("id");

            s.id = id;

            liste.add(s);
        }

        return liste;
    }

    public static Serie findById(int id) throws SQLException {
        System.out.println("7.2) Recupere la Serie de l'id passe en parametre");
        Serie res=null;
        Connection connect = DBConnection.getInstance().getConnection();
        String SQLPrep = "SELECT * FROM Serie WHERE id=?;";
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.setInt(1, id);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        if (rs.next()) {
            String nom = rs.getString("nom");
            String genre = rs.getString("genre");
            res=new Serie(nom,genre);
            res.setId(id);
        }
        return res;
    }

    public static List<Serie> findByName(String search) throws SQLException {
        System.out.println("7.3) Recupere les series de search d'apres le nom");
        String SQLPrep = "SELECT * FROM Serie WHERE nom LIKE ?;";
        Connection connect = DBConnection.getInstance().getConnection();
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.setString(1,'%'+search+'%');
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        List<Serie> liste = new ArrayList<Serie>();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String genre = rs.getString("genre");
            Serie s = new Serie(nom, genre);
            int id = rs.getInt("id");
            s.id = id;
            liste.add(s);
        }
        return liste;
    }

    public static List<Serie> findByGenre(String search) throws SQLException{
        System.out.println("7.4) Recupere les series de search d'apres le genre");
        String SQLPrep = "SELECT * FROM Serie WHERE nom LIKE ?;";
        Connection connect = DBConnection.getInstance().getConnection();
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.setString(1,search);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        List<Serie> liste = new ArrayList<Serie>();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String genre = rs.getString("genre");
            Serie s = new Serie(nom, genre);
            int id = rs.getInt("id");
            s.id = id;
            liste.add(s);
        }
        return liste;
    }

    public static void createTable() throws SQLException {
        String createString = "CREATE TABLE Serie ( " + "ID INTEGER  AUTO_INCREMENT, " + "NOM varchar(40) NOT NULL, "
                + "GENRE varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
        Connection connect = DBConnection.getInstance().getConnection();
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
        System.out.println("8.1) creation table serie.Serie");
    }

    public static void deleteTable() throws SQLException {
        String drop = "DROP TABLE Serie";
        Connection connect = DBConnection.getInstance().getConnection();
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
        System.out.println("8.1) Supprime table serie.Serie");
    }

    public void save() throws SQLException {
        if (id == -1)
            saveNew();
        else
            update();
    }

    private void saveNew() throws SQLException {
        String SQLPrep = "INSERT INTO Serie (nom, genre) VALUES (?,?);";
        Connection connect = DBConnection.getInstance().getConnection();
        PreparedStatement prep = connect.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.nom);
        prep.setString(2, this.genre);
        prep.executeUpdate();
        System.out.println("8.5) ajout " + nom);

        // recuperation de la derniere ligne ajoutee (auto increment)
        // recupere le nouvel id
        int autoInc = -1;
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
            autoInc = rs.getInt(1);
        }
        this.id = autoInc;
        System.out.print("  ->  id utilise lors de l'ajout : ");
        System.out.println(autoInc);
        System.out.println();

    }

    private void update() throws SQLException {
        String SQLprep = "update Personne set nom=?, genre=? where id=?";
        PreparedStatement prep = DBConnection.getConnection().prepareStatement(SQLprep);
        prep.setString(1, this.nom);
        prep.setString(2, this.genre);
        prep.setInt(3, this.id);
        prep.execute();
    }

    private void delete() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        PreparedStatement prep = connect.prepareStatement("DELETE FROM Serie WHERE id=?");
        prep.setInt(1, this.id);
        prep.execute();
        this.id=-1;
        System.out.println("8.4) Suppression serie");
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}