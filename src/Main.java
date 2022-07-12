import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre( "Stamford",5,10 );
        List<Theatre.Seat> copySeat = new ArrayList<>(theatre.seats);
        theatre.getSeats();
        printSeat( copySeat);
        if (theatre.reserved(  "D12")){
            System.out.println("Please pay for A02");
        } else {
            System.out.println("Seat already reserved");
        }


    }
    public static void printSeat(List<Theatre.Seat> list){
        for (Theatre.Seat seat : list){
            System.out.print(seat.getSeatName() + " $" + seat.getPrice() + ", ");
        }
    }
}