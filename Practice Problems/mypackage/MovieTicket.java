package mypackage;

public class MovieTicket {
     private String seatno;
    public String moviename;
    protected int screenid;
    protected double price;

    public MovieTicket(String seatno, String moviename, int screenid, double price){
        this.seatno = seatno;
        this.moviename = moviename;
        this.screenid = screenid;
        this.price = price;
    }
    public String getSeatno(){
        return seatno;
    }
    
}
