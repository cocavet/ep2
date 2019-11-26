package uoc.ded.practica.model.Screen;

public class Screen {
    int idScreen;
    int points;

    public Screen( int screenId, int points){
        this.idScreen = screenId;
        this.points = points;
   }

   /**
    * @return the idScreen
    */
   public int getIdScreen() {
       return idScreen;
   }

   /**
    * @return the points
    */
   public int getPoints() {
       return points;
   }

   /**
    * @param idScreen the idScreen to set
    */
   public void setIdScreen(int idScreen) {
       this.idScreen = idScreen;
   }

   /**
    * @param points the points to set
    */
   public void setPoints(int points) {
       this.points = points;
   }
}