package uoc.ded.practica;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.Game.*;
import uoc.ei.tads.Iterador;

public class Play4FunEP2Test {

    private Play4Fun play4Fun;

    @Before
    public void setUp() throws Exception {
        this.play4Fun = FactoryPlay4Fun.getMyLimon();
    }

    @After
    public void tearDown() {
        this.play4Fun = null;
    }


    /**
     * *feature*: (sobre la que fem @test): addUser del TAD Play4Fun
     * *given*: Hi ha 10 usuaris en el sistema
     * *scenario*:
     *   - S'afegeix un nou usuari en el sistema
     *   - S'afegeix un segon usuari en el sistema
     *   - Es modifiquen les dades del segon usuari inserir
     */
    @Test
    public void testAddUser() {

        // GIVEN:
        Assert.assertEquals(10, this.play4Fun.numUsers());
        //

        this.play4Fun.addUser("idUser1000", "Robert", "Lopez");
        Assert.assertEquals("Robert", this.play4Fun.getUser("idUser1000").getName());
        Assert.assertEquals(11, this.play4Fun.numUsers());

        this.play4Fun.addUser("idUser9999", "XXXXX", "YYYYY");
        Assert.assertEquals("XXXXX", this.play4Fun.getUser("idUser9999").getName());
        Assert.assertEquals(12, this.play4Fun.numUsers());

        this.play4Fun.addUser("idUser9999", "Lluis", "Casals");
        Assert.assertEquals("Lluis", this.play4Fun.getUser("idUser9999").getName());
        Assert.assertEquals("Casals", this.play4Fun.getUser("idUser9999").getSurname());
        Assert.assertEquals(12, this.play4Fun.numUsers());
    }



    /**
     *
     * *feature*: (sobre la que fem @test): addGame del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema
     * *scenario*:
     *   - S'afegeix un nou joc en el sistema
     *   - S'afegeix un segon joc en el sistema
     * @throws GameAlreadyExistsException 
     */
    @Test
    public void testAddGame()
            throws GameAlreadyExistsException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        //

        this.play4Fun.addGame("idGame7", "Description7");
        this.play4Fun.addGame("idGame8", "DescriptionXXX");
        Assert.assertEquals(8, this.play4Fun.numGames());
    }

    /**
     *
     * *feature*: (sobre la que fem @test): addGame del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema
     * *scenario*:
     *   - S'afegeix un nou joc en el sistema
     *   - S'afegeix un segon joc en el sistema
     *   - S'afegeix un tercer joc en el sistema que ja existeix
     */
    @Test  (expected = GameAlreadyExistsException.class)
    public void testAddGameAlreadyExists()
            throws GameAlreadyExistsException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        //

        Assert.assertEquals(6, this.play4Fun.numGames());
        this.play4Fun.addGame("idGame7", "Description7");
        this.play4Fun.addGame("idGame8", "DescriptionXXX");
        Assert.assertEquals(8, this.play4Fun.numGames());

        this.play4Fun.addGame("idGame8", "Description8");
    }


    /**
     *
     * *feature*: (sobre la que fem @test): addLevel del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema i dos nivells en el joc idGame1
     *
     * *scenario*:
     *   - S'afegeixen dos nivells sobre un joc idGame1
     *   - S'afegeixen dos nivells sobre un joc idGame2
     * @throws LevelAlreadyExistsException 
     */
    @Test
    public void testLevel() throws DEDException, LevelAlreadyExistsException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame1"));
        Assert.assertEquals(0, this.play4Fun.numLevels("idGame2"));
        //

        this.play4Fun.addLevel("idLevel3","idGame1", "LEVEL1", 10, 3 );
        Assert.assertEquals(3, this.play4Fun.numLevels("idGame1"));

        this.play4Fun.addLevel("idLevel4","idGame1", "LEVEL1", 5, 2 );
        Assert.assertEquals(4, this.play4Fun.numLevels("idGame1"));

        this.play4Fun.addLevel("idLevel1","idGame2", "LEVEL1", 8, 5);
        Assert.assertEquals(1, this.play4Fun.numLevels("idGame2"));

        this.play4Fun.addLevel("idLevel2","idGame2", "LEVEL1", 3, 2 );
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame2"));

    }

    /**
     *
     * *feature*: (sobre la que fem @test): addLevel del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema
     *
     * *scenario*:
     *   - S'afegeix un nivell idLevel1 sobre un joc idGame1
     *   - S'afegeix un nou nivell idLevel1 (ja existent) sobre el joc idGame1
     * @throws LevelAlreadyExistsException 
     */
    @Test(expected = LevelAlreadyExistsException.class)
    public void testLevelAlreadyExists() throws DEDException, LevelAlreadyExistsException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame1"));
        //

        this.play4Fun.addLevel("idLevel1","idGame1", "LEVEL1", 10, 3 );
        Assert.assertEquals(1, this.play4Fun.numLevels("idGame1"));

        this.play4Fun.addLevel("idLevel1","idGame1", "LEVEL1", 5, 2 );
    }

    /**
     *
     * *feature*: (sobre la que fem @test): addScreen del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema, dos nivells en el joc idGame1 i tres pantalles
     *
     * *scenario*:
     *    - S'afegeixen tres pantalles sobre el nivell Level1 i joc Game1
     * @throws LevelFullException 
     */
    @Test
    public void testScreen() throws DEDException, LevelFullException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame1"));
        Assert.assertEquals(3, this.play4Fun.numScreens("idGame1", "idLevel1"));
        Assert.assertEquals(0, this.play4Fun.numLevels("idGame2"));

        //

        this.play4Fun.addScreen("idGame1", "idLevel1", 3, 5);
        Assert.assertEquals(4, this.play4Fun.numScreens("idGame1", "idLevel1"));

        this.play4Fun.addScreen("idGame1", "idLevel1", 4, 6);
        Assert.assertEquals(5, this.play4Fun.numScreens("idGame1", "idLevel1"));

        this.play4Fun.addScreen("idGame1", "idLevel1", 5, 4);
        Assert.assertEquals(6, this.play4Fun.numScreens("idGame1", "idLevel1"));

    }

    /**
     *
     * *feature*: (sobre la que fem @test): addScreen del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema, dos nivells en el joc idGame1 i tres pantalles
     *
     * *scenario*:
     *    - S'afegeixen tres pantalles sobre el nivell Level1 i joc Game1
     *    - S'intenta afegir una cuarta pantalla que supera el màxim nombre de pantalles
     * @throws LevelFullException 
     */
    @Test (expected = LevelFullException.class)
    public void testScreenLevelFull() throws LevelFullException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame1"));
        Assert.assertEquals(3, this.play4Fun.numScreens("idGame1", "idLevel1"));
        Assert.assertEquals(0, this.play4Fun.numLevels("idGame2"));
        //

        this.play4Fun.addScreen("idGame1", "idLevel1", 3, 25);
        Assert.assertEquals(4, this.play4Fun.numScreens("idGame1", "idLevel1"));

        this.play4Fun.addScreen("idGame1", "idLevel1", 4, 20);
        Assert.assertEquals(5, this.play4Fun.numScreens("idGame1", "idLevel1"));

        this.play4Fun.addScreen("idGame1", "idLevel1", 5, 20);
        Assert.assertEquals(6, this.play4Fun.numScreens("idGame1", "idLevel1"));

        this.play4Fun.addScreen("idGame1", "idLevel1", 6, 20);

    }


    /**
     *
     * *feature*: (sobre la que fem @test): playGame del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema, dos nivells en el joc idGame1 i tres pantalles
     *
     * *scenario*:
     *    - S'indica que diferents usuaris juguen a un determinat joc
     */
    @Test
    public void testPlayGameAndTOPGames() throws DEDException {
        // GIVEN:
        Assert.assertEquals(6, this.play4Fun.numGames());
        Assert.assertEquals(2, this.play4Fun.numLevels("idGame1"));
        Assert.assertEquals(0, this.play4Fun.numLevels("idGame2"));
        //

        this.play4Fun.playGame("idUser4", "idGame2");

        this.play4Fun.playGame("idUser1", "idGame1");
        this.play4Fun.playGame("idUser2", "idGame1");
        this.play4Fun.playGame("idUser3", "idGame1");


        Iterador<Game> itGames = this.play4Fun.topGames();

        Game game1 = itGames.seguent();
        Assert.assertEquals("idGame1", game1.getIdGame());

        Game game2 = itGames.seguent();
        Assert.assertEquals("idGame2", game2.getIdGame());

        this.play4Fun.playGame("idUser4", "idGame3");
        this.play4Fun.playGame("idUser5", "idGame3");
        this.play4Fun.playGame("idUser6", "idGame3");
        this.play4Fun.playGame("idUser7", "idGame3");

        itGames = this.play4Fun.topGames();

        Game game3 = itGames.seguent();
        Assert.assertEquals("idGame3", game3.getIdGame());

        game1 = itGames.seguent();
        Assert.assertEquals("idGame1", game1.getIdGame());

        game2 = itGames.seguent();
        Assert.assertEquals("idGame2", game2.getIdGame());

    }


    /**
     *
     * *feature*: (sobre la que fem @test): nextScreen del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema, dos nivells en el joc idGame1 i tres pantalles
     *
     * *scenario*:
     *    - S'indica que un usuari pasa a la següent pantalla amb un nombre de punts SUPERIOR al demanat
     */
    /*@Test
    public void testNextScreenAndTopUsersForScreen() throws DEDException {

        Move mUser1;
        Move mUser2;
        Move mUser3;

        this.play4Fun.nextScreen("idUser1", "idGame1", "idLevel1", 0, 25);

        Iterador<Move> it = this.play4Fun.topUsersForScreen("idGame1", "idLevel1", 0);
        mUser1 = it.seguent();
        Assert.assertEquals("nextScreen", "idUser1", mUser1.getUser().getId());
        this.play4Fun.nextScreen("idUser3", "idGame1", "idLevel1", 0, 28);

        this.play4Fun.nextScreen("idUser2", "idGame1", "idLevel1", 0, 35);

        it = this.play4Fun.topUsersForScreen("idGame1", "idLevel1", 0);

        mUser2 = it.seguent();
        Assert.assertEquals("nextScreen", "idUser2", mUser2.getUser().getId());
        Assert.assertEquals("nextScreen", 35, mUser2.getPoints());

        mUser3 = it.seguent();
        Assert.assertEquals("nextScreen", "idUser3", mUser3.getUser().getId());
        Assert.assertEquals("nextScreen", 28, mUser3.getPoints());

        mUser1 = it.seguent();
        Assert.assertEquals("nextScreen", "idUser1", mUser1.getUser().getId());
        Assert.assertEquals("nextScreen", 25, mUser1.getPoints());
    }*/



    /**
     *
     * *feature*: (sobre la que fem @test): nextScreen del TAD Play4Fun
     * *given*: Hi ha 6 jocs en el sistema, dos nivells en el joc idGame1 i tres pantalles
     *
     * *scenario*:
     *    - S'indica que un usuari pasa a la següent pantalla amb un nombre de punts INFERIOR al demanat
     * @throws NoEnoughPointsException 
     */
    @Test(expected = NoEnoughPointsException.class)
    public void testNextScreenEnoughtPoints() throws DEDException, NoEnoughPointsException {
        this.play4Fun.nextScreen("idUser1", "idGame1", "idLevel1", 0, 5);
    }
}
