import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie{
    private String movieName;
    private String showTime;

    public Movie(String movieName, String showTime){
        this.movieName = movieName;
        this.showTime = showTime;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getShowTime(){
        return showTime;
    }
}

class Seat{
    private String type;
    private double price;
    private boolean isBooked;

    public Seat(String type, double price){
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public boolean isBooked(){
        return isBooked;
    }

    public void book(){
        this.isBooked = true;
    }

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }
}

class Booking {
    private Seat[][] seats;
    private double totalCost;
    private List<String> selectedSeats;

    public Booking(int rows, int cols){
        seats = new Seat[rows][cols];
        totalCost = 0;
        selectedSeats = new ArrayList<>();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i < rows / 2){
                    seats[i][j] = new Seat("Regular", 100);
                }
                else{
                    seats[i][j] = new Seat("Premium", 200);
                }
            }
        }
    }

    public void displaySeats(){
        System.out.println("Seat Layout:");
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < seats[i].length; j++){
                if (seats[i][j].isBooked()){
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col){
        if(row < 0 || col < 0 || row >= seats.length || col >= seats[0].length){
            System.out.println("Invalid seat position!");
            return false;
        }

        if(seats[row][col].isBooked()){
            System.out.println("Seat already booked!");
            return false;
        }

        seats[row][col].book();
        totalCost += seats[row][col].getPrice();
        selectedSeats.add("(" + (row + 1) + "," + (col + 1) + ")");
        return true;
    }

    public void showConfirmation(Movie movie){
        System.out.println("\nBooking Confirmation");
        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Show Time: " + movie.getShowTime());
        System.out.println("Selected Seats: " + String.join(", ", selectedSeats));
        System.out.println("Total Cost: ₹" + totalCost);
        System.out.println("Booking Confirmed!");
    }
}

public class project{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Movie movie = new Movie("Avengers: Endgame", "7:00 PM");
        Booking booking = new Booking(3, 4);

        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Show Time: " + movie.getShowTime());

        int choice;
        do {
            booking.displaySeats();
            System.out.println("Enter row and column to book (1-based index): ");
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            booking.bookSeat(row, col);

            System.out.println("Book another seat? (1-Yes / 0-No)");
            choice = sc.nextInt();

        } while (choice == 1);

        booking.showConfirmation(movie);
        sc.close();
    }
}

// Sample input -->

// Movie: Avengers: Endgame
// Show Time: 7:00 PM
// Seat Layout:
// O O O O
// O O O O
// O O O O
// Enter row and column to book (1-based index):
// 1 2
// Book another seat? (1-Yes / 0-No)
// 1
// Seat Layout:
// O X O O
// O O O O
// O O O O
// Enter row and column to book (1-based index):
// 2 4
// Book another seat? (1-Yes / 0-No)
// 1
// Seat Layout:
// O X O O
// O O O X
// O O O O
// Enter row and column to book (1-based index):
// 1 2
// Seat already booked!
// Book another seat? (1-Yes / 0-No)
// 0


// Sample output -->

// Booking Confirmation
// Movie: Avengers: Endgame
// Show Time: 7:00 PM
// Selected Seats: (1,2), (2,4)
// Total Cost: ₹300.0
// Booking Confirmed!