package uoc.ded.practica;

import uoc.ded.practica.model.User.*;
import uoc.ei.tads.Iterador;

import java.util.*;

import uoc.ded.practica.exceptions.GameAlreadyExistsException;
import uoc.ded.practica.exceptions.GameNotFoundException;
import uoc.ded.practica.exceptions.LevelAlreadyExistsException;
import uoc.ded.practica.exceptions.LevelFullException;
import uoc.ded.practica.exceptions.LevelNotFoundException;
import uoc.ded.practica.exceptions.NoEnoughPointsException;
import uoc.ded.practica.exceptions.ScreenNotFoundException;
import uoc.ded.practica.model.Game.*;
import uoc.ded.practica.model.Level.*;
import uoc.ded.practica.model.Move.Move;
import uoc.ded.practica.model.Screen.*;

/**
 * Definició del TAD de gestió de la plataforma de jocs per tauletes i mòbils
 */
public interface Play4Fun {


    /**
     * dimensió del contenidor de jocs
     */
    public static final int J = 250;


    /**
     * dimensió del contenidor de les pantalles del nivell
     */
    public static final int PN = 8;

    /**
     * dimensió del contenidor dels topPlayers
     */
    public static int TOP_GAMES = 10;

    /**
     * States
     */
    public static List<User> USER_STATE = new ArrayList<User>();
    public static List<Game> GAME_STATE= new ArrayList<Game>();
    public static List<Level> LEVEL_STATE= new ArrayList<Level>();
    public static List<Screen> SCREEN_STATE= new ArrayList<Screen>();

    /**
     * Mètode que afegeix un nou usuari al sistema
     *
     * @param idUser  identificador de l'usuari
     * @param name    nom de l'usuari
     * @param surname cognom de l'usuari
     * @pre cert
     * @post si el codi d’usuari és nou, els usuaris seran els mateixos
     * més un nou usuari amb les dades indicades. Sinó les dades
     * de l’usuari s’hauran actualitzat amb les noves.
     */
    public void addUser(String idUser, String name, String surname);


    /**
     * Mètode que permet afegir un nou joc al sistema
     *
     * @param idGame identificador del joc
     * @param description descripció del joc
     *
     * @pre cert
     * @post els jocs són els mateixos més un de nou amb les dades
     * indicades. Si ja hi ha un joc amb el nom especificat tornarà un error
     */
    public void addGame(String idGame, String description)
            throws GameAlreadyExistsException;


    /**
     * Mètode que permet afegir un nou nivell a un joc especificat
     *
     * @param idLevel identificador del nivell
     * @param idGame identificador del joc
     * @param name nom del joc
     * @param hardness nivell de dificultat
     * @param nLevelScreens nombre de pantalles que tindrà
     *
     * @pre existeix un joc amb el idGame especificat
     * @post els nivells són els mateixos més un de nou amb les dades
     * indicades. Si ja hi ha un nivell amb l’identificador
     * especificat tornarà un error.
     */
    public void addLevel(String idLevel, String idGame, String name, int hardness, int nLevelScreens)
            throws LevelAlreadyExistsException;


    /**
     * Mètode que permet afegir una pantall a un nivell d'un joc
     *
     * @param idGame identificador del joc
     * @param idLevel identificador del nivell
     * @param idScreen identificador de la pantalla
     * @param points nombre de punts necessaris per passar la pantalla
     *
     * @pre existeix un joc amb el gameID i el levelID especificats.
     * @post Si levelScreenID no existeix, el nivell identificat amb levelID
     * del joc gameID té les mateixes pantalles més una de nova amb les dades
     * indicades. Sinó les dades de la pantalla s’hauran actualitzat amb les
     * noves. Si el nivell ja té totes les pantalles definides tornarà un error.
     *
     * @throws LevelFullException
     */
    public void addScreen(String idGame, String idLevel, int idScreen, int points )
            throws LevelFullException;


    /**
     * Mètode que permet jugar a una nova partida
     *
     * @param idUser identificador del jugador
     * @param idGame identificador del joc
     *
     * @pre existeix un usuari amb el userID especificat i un joc amb el gameID especificat.
     * @post  S’ha actualitzat l’estructura dels 10 jocs més utilitzats.
     *
     */
    public void playGame(String idUser, String idGame);


    /**
     * Mètode que permet passar una pantalla en un joc
     *
     * @param idUser identificador de l'usuari
     * @param idGame identificador del joc
     * @param idLevel identificador del nivell
     * @param levelScreenId identificador del nivell dins de la pantalla
     * @param points punts aconseguits
     *
     * @pre existeix un usuari, un joc, un nivell i una pantalla amb els identificadors especificats
     * @post Si el nombre de punts aconseguits es menor que el nombre de punts que es
     * requereix per passar la pantalla tornarà un error. Si el nombre de punts aconseguits
     * es major que el nombre de punts que es requereix per passar la
     * pantalla s’actualitza l’estructura que guarda els usuaris que han passat la pantalla.
     */
    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points)
            throws NoEnoughPointsException;


    /**
     * Mètode que proporciona un iterador con els 10 jugadors que han passat la
     * pantalla ordenats de major a menor segons la puntuació obtinguda.
     *
     * @param idGame identificador del juego
     * @param idLevel identificador del nivel
     * @param levelScreenID identificador de la pantalla
     * @return
     * @throws GameNotFoundException en caso que no exista el juego
     * @throws LevelNotFoundException en caso que no exista el nivel
     *
     * @pre cierto
     * @post devuelve un iterador para recorrer los 10 jugadores que han pasado
     * la pantalla ordenados de mayor a menor según la puntuación obtenida.
     * Si el juego, el nivel o la pantalla dentro del nivel no existen
     * devolverá un error.
     */
    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID)
            throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException;


    /**
     * Mètode que proporciona un iterador con els juegos més utilitzats de major a menor
     * @return returna l'iterador de juegos
     *
     * @pre  cierto
     * @post devuelve un iterador para recorrer los juegos más utilizados
     * ordenados de mayor a menor.
     */
    public Iterador<Game> topGames();

    /**
     * Mètode que proporciona el nombre d'usuaris
     * @return retorna el total d'usuaris
     */
    public int numUsers();

    /**
     * Mètode que proporciona el nombre de jocs
     * @return retorna el total de jocs
     */
    public int numGames();


    /**
     * Mètode que proporciona el nombre de nivells d'un joc
     * @param idGame el joc
     * @return retorna el nivell d'un joc. Si el joc no existeix retorna 0
     */
    public int numLevels(String idGame);

    /**
     * Mètode que proporciona el nombre de pantalles d'un nivell d'un joc
     * @param idGame el joc
     * @param idLevel el nivell
     * @return retorna el nombre de pantalles d'un nivell d'un joc. Si el joc o
     * nivell no existeix, retorna 0
     */
    public int numScreens(String idGame, String idLevel);

    /**
     * Mètode que proporciona un usuari identificat
     *
     * @param idUser identificador de l'usuari
     * @return retorna l'usuari identificcat per idUser o null en cas que no existeixi
     */
    public User getUser(String idUser);
}




