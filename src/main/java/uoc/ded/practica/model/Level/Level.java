package uoc.ded.practica.model.Level;
import uoc.ded.practica.model.Screen.*;

public class Level {
    String idLevel;
    int hardness;
    int nLevelScreens;
    Screen[] screens;

    public Level(String idLevel, int hardness, int nLevelScreens) {
        this.idLevel = idLevel;
        this.hardness = hardness;
        this.nLevelScreens = nLevelScreens;
        this.screens = null;
   }

   /**
    * @return the hardness
    */
   public int getHardness() {
       return hardness;
   }

   /**
    * @return the idLevel
    */
   public String getIdLevel() {
       return idLevel;
   }

   /**
    * @return the nLevelScreens
    */
   public int getnLevelScreens() {
       return nLevelScreens;
   }

   /**
    * @param hardness the hardness to set
    */
   public void setHardness(int hardness) {
       this.hardness = hardness;
   }

   /**
    * @param idLevel the idLevel to set
    */
   public void setIdLevel(String idLevel) {
       this.idLevel = idLevel;
   }

   /**
    * @param nLevelScreens the nLevelScreens to set
    */
   public void setnLevelScreens(int nLevelScreens) {
       this.nLevelScreens = nLevelScreens;
   }
   /**
    * @param nLevelScreens the nLevelScreens to set
    */
   public void setScreen(int screenId, int points) {
        this.screens[this.screens.length + 1] = new Screen(screenId, points);
   }
}