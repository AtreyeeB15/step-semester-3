package otherpackage;
import mypackage.MovieTicket;

public class PremiumMovieTicket extends MovieTicket {
    public PremiumMovieTicket(String seatno, String moviename, int screenid, double price) {
        super(seatno, moviename, screenid, price);
    }
    public void testProtectedAccess(MovieTicket parentref, PremiumMovieTicket ownref){
        double p1=ownref.price; // allowed, same class
        /*A subclass in a different package can only access a protected member if it does so through its own type (or one of its own subclasses), NOT through a parent type reference. */
       
        double p2=this.price; 
    }
    
}
