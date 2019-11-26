package uoc.ded.practica.model.Game;
import java.util.ArrayList;
import java.util.List;

import uoc.ded.practica.model.Level.*;

public class Game {
    String idGame;
    String description;
    List<Level> levels;

    public Game(String idGame, String description){
        this.idGame = idGame;
        this.description = description;
        this.levels = new ArrayList<Level>();
   }

   /**
    * @return the description
    */
   public String getDescription() {
       return description;
   }

   /**
    * @return the idGame
    */
   public String getIdGame() {
       return idGame;
   }

   /**
    * @param description the description to set
    */
   public void setDescription(String description) {
       this.description = description;
   }

   /**
    * @param idGame the idGame to set
    */
   public void setIdGame(String idGame) {
       this.idGame = idGame;
   }

    /**
    * @param idLevel,hardness,nLevelScreens the new Level to set
    */
    public void setLevel(String levelId, int hardness, int nLevelScreens) {
    	this.levels.add(new Level(levelId, hardness, nLevelScreens));
    }

    /**
    * @return levels of game
    */
    public List<Level> getLevels() {
        return levels;
    }
}