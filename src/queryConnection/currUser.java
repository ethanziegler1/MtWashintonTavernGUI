package queryConnection;
public class currUser {
  public String userID; 

  currUser(String user){
      this.userID = user;
}


  public String getCurrentUser(){
      return this.userID;
} 

  public void setCurrentUser(String user){
     this.userID = user;
}   
 }