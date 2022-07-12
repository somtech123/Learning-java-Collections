import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Theatre {
    private final String theatreName;
    public List<Seat> seats = new ArrayList<>();
    private static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()){
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public Theatre(String theatreName, int seatRow, int seatPerRow) {
        this.theatreName = theatreName;
        int row = 'A' + (seatRow - 1);
        for (char rowNum = 'A'; rowNum <= row; rowNum++){
            for (int num = 1; num <= seatPerRow; num++){
                double price = 14.00;
                if ((rowNum < 'D') && (num >= 4 && num <= 9)){
                    price = 12.00;
                } else if ((rowNum > 'F') || (num < 4 || num > 9)) {
                    price = 7.00;
                }
                Seat seat = new Seat( rowNum + String.format( "%02d",num ),price );
                this.seats.add( seat );
            }
        }
    }
    public boolean reserved(String seatNum){
        Seat requestedSeat = new Seat( seatNum,0 );
        int foundPosition = Collections.binarySearch( seats,requestedSeat,null );
        if (foundPosition >= 0){
            return this.seats.get( foundPosition ).reserve();
        } else {
            System.out.println("No seat reservation for " + seatNum);
            return false;
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {
        private final String seatName;
        private boolean reserved = false;
        private double price;

        public Seat(String seatName, double price) {
            this.seatName = seatName;
            this.price = price;
        }

        public String getSeatName() {
            return seatName;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatName.compareToIgnoreCase( seat.getSeatName() );
        }
        public boolean reserve(){
            if (!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatName + " reserved ");
                return true;
            } else {
                return false;
            }
        }
        public boolean cancel(){
            if (this.reserved){
                this.reserved = false;
                System.out.println("Seat " + seatName + " reservation cancelled");
                return true;
            } else {
                return false;
            }
        }
    }
}
