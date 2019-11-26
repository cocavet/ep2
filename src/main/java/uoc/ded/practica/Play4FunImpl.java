package uoc.ded.practica;

import uoc.ded.practica.exceptions.*;
import uoc.ded.practica.model.User.*;
import uoc.ded.practica.model.Game.*;
import uoc.ded.practica.model.Level.*;
import uoc.ded.practica.model.Move.*;
import uoc.ded.practica.model.Screen.*;
import uoc.ded.practica.util.*;
import uoc.ei.tads.Iterador;


public class Play4FunImpl extends Finders implements Play4Fun {
	Play4FunImpl() {
	  System.out.println("User "+USER_STATE.size());
	  System.out.println("GAME "+GAME_STATE.size());
	  System.out.println("LEVEL :"+LEVEL_STATE.size());
	  System.out.println("SCREEN "+SCREEN_STATE.size());
	  System.out.println("-------------");
	}

	public void addUser(String idUser, String name, String surname) {
        User user = findUserById(USER_STATE, idUser);

        if (user != null) {
            user.setName(name);
            user.setSurname(surname);
        } else {
        	USER_STATE.add(new User(idUser, name, surname));
        }
    }

  
	public void addGame(String idGame, String description) throws GameAlreadyExistsException {
        Game game = findGameById(GAME_STATE, idGame);
        
        if (game != null) {
            throw new GameAlreadyExistsException("The game already exists");
        } else {
        	GAME_STATE.add(new Game(idGame, description));
        }
    }


	public void addLevel(String levelId, String idGame, String name, int hardness, int nLevelScreens)
            throws LevelAlreadyExistsException {
        Level level = findLevelById(LEVEL_STATE, idGame);

        if (level != null) {
            //throw new LevelAlreadyExistsException("The level already exists");
        } else {
        	LEVEL_STATE.add(new Level(levelId, hardness, nLevelScreens));
        	Game game = this.findGameById(GAME_STATE, idGame);
            game.setLevel(levelId, hardness, nLevelScreens);
            
        }
    }
    
    public void addScreen(String idGame, String idLevel, int idScreen, int points) throws LevelFullException{
        Screen screen = this.findScreenById(SCREEN_STATE, idScreen);
        Level level = this.findLevelById(LEVEL_STATE, idLevel);

        
        if(this.numScreens(idGame, idLevel) >= level.getnLevelScreens()) {
        	//throw new LevelFullException();
        }

        if (screen != null ) {
        	screen.setPoints(points);
        } else {
        	SCREEN_STATE.add(new Screen(idScreen, points));
        }
    }   

    public void playGame(String idUser, String idGame) {
        
    }

    public void nextScreen(String idUser, String idGame, String idLevel, int levelScreenId, int points)
            throws NoEnoughPointsException {

    }

    public Iterador<Move> topUsersForScreen(String idGame, String idLevel, int levelScreenID)
            throws GameNotFoundException, LevelNotFoundException, ScreenNotFoundException {
    	
    	return null;

    }

    public Iterador<Game> topGames() {
		return null;
    	
    }

    public int numUsers() {
        return USER_STATE.size();
    }

    public int numGames() {
        return GAME_STATE.size();
    }

    public int numLevels(String idGame) {
        return LEVEL_STATE.size();
    }

    public int numScreens(String idGame, String idLevel) {
        Game game = findGameById(GAME_STATE, idGame);
        
        return game.getLevels().size();
    }
    
    public User getUser(String idUser) {
        return findUserById(USER_STATE, idUser);
    }
}
