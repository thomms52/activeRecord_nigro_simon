import org.junit.*;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerieTest {

    @Before
    public void avant() throws SQLException{
        Serie.createTable();
        (new Serie("TOTO", "toto")).save();
        (new Serie("TATA", "tata")).save();
        (new Serie("TITI", "titi")).save();
    }

    @After
    public void apres() throws SQLException {
        Serie.deleteTable();
    }

    @Test
    public void testFindAll() throws SQLException{
        //prep donnes
        List<Serie> liste;
        //utilisation methode a tester
        liste=Serie.findAll();
        //validation resultats
        assertEquals("3 series normalement",3,liste.size());
    }

}
