package uoc.ded.practica.util;

import uoc.ded.practica.model.User.*;

import java.util.List;

import uoc.ded.practica.model.Game.*;
import uoc.ded.practica.model.Level.*;
import uoc.ded.practica.model.Screen.*;

public class Finders {
	
    public User findUserById(List<User> userState, String userId) {
    	if(userState == null) {
    		return null;
    	}
        for (User user : userState) {
            if (user.getIdUser() == userId) {              
                return user;
            }
        }

        return null;
    }

    public Game findGameById(List<Game> gameState, String gameId) {
    	Game found = null;
        for (Game game : gameState) {
            if (game.getIdGame() == gameId) {
            	found = game;
            }
        }
        
        if(found != null) {
        	return found;
        }else{
        	return null;
        }

        
    }

    public Level findLevelById(List<Level> levelState, String levelId) {
        for (Level level : levelState) {
            if (level.getIdLevel() == levelId) {
                return level;
            }
        }

        return null;
    }

    public Screen findScreenById(List<Screen> screenState, int idScreen) {
        for (Screen screen : screenState) {
            if (screen.getIdScreen() == idScreen) {
                return screen;
            }
        }

        return null;
    }
}