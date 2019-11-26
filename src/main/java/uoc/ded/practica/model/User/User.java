package uoc.ded.practica.model.User;

public class User {
    String idUser;
    String name;
    String surname;

    public User( String idUser, String name, String surname){
        this.idUser = idUser;  
        this.name = name;
        this.surname = surname;
   }

   /**
    * @return the idUser
    */
   public String getIdUser() {
       return idUser;
   }

   /**
    * @return the name
    */
   public String getName() {
       return name;
   }

   /**
    * @return the surname
    */
    
   public String getSurname() {
       return surname;
   }

   /**
    * @param idUser the idUser to set
    */
   public void setIdUser(String idUser) {
       this.idUser = idUser;
   }

   /**
    * @param name the name to set
    */

   public void setName(String name) {
       this.name = name;
   }
   /**
    * @param surname the surname to set
    */

   public void setSurname(String surname) {
       this.surname = surname;
   }
}