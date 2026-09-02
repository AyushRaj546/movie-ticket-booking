import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Cinema cinema;
    private static BookingService bookingService;
    private static List<Booking> allBookings = new ArrayList<>();
    public static void main(String[] args) {
        setupCinema();
        PriceCalculator priceCalculator = new PriceCalculator();
        TicketPrinter ticketPrinter = new TicketPrinter();
        bookingService = new BookingService(priceCalculator,ticketPrinter);
        while (true)
        {
            System.out.println("\n=== MOVIE TICKET BOOKING ===");
            System.out.println("1. List Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. My Bookings");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: listMovies(); break;
                case 2: bookTickets(); break;
                case 3: cancelBooking(); break;
                case 4: showMyBookings(); break;
                case 0: System.out.println("Goodbye!"); System.exit(0);
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void setupCinema() {
        cinema = new Cinema("PVR Cinemas");

        Movie movie1 = new Movie("3 Idiots", "Hindi", 170);
        Movie movie2 = new Movie("Interstellar", "English", 169);
        cinema.addMovie(movie1);
        cinema.addMovie(movie2);

        Screen screen1 = new Screen(1, 5, 10);
        Screen screen2 = new Screen(2, 5, 10);
        cinema.addScreen(screen1);
        cinema.addScreen(screen2);

        Show show1 = new Show(movie1, screen1, LocalDateTime.of(2026, 9, 1, 18, 0));
        Show show2 = new Show(movie1, screen2, LocalDateTime.of(2026, 9, 1, 21, 0));
        Show show3 = new Show(movie2, screen1, LocalDateTime.of(2026, 9, 2, 19, 0));
        allShows.add(show1);
        allShows.add(show2);
        allShows.add(show3);
    }
    // We'll need a list of all shows. Let's add a static List<Show> in Main.
    private static List<Show> allShows = new ArrayList<>();

    // We'll fill allShows in setupCinema() after creating shows.
    // So adjust setupCinema() to add shows to allShows.

    private static void listMovies() {
        System.out.println("Currently playing movies:");
        List<Movie> movies = cinema.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            System.out.println((i+1) + ". " + m.getTitle() + " (" + m.getLanguage() + ", " + m.getDuration() + " min)");
        }
    }
    private static void bookTickets() {
        // 1. Choose a movie
        List<Movie> movies = cinema.getMovies();
        System.out.println("Select a movie:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1) + ". " + movies.get(i).getTitle());
        }
        System.out.print("Enter movie number: ");
        int movieIdx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (movieIdx < 0 || movieIdx >= movies.size()) {
            System.out.println("Invalid movie.");
            return;
        }
        Movie selectedMovie = movies.get(movieIdx);

        // 2. List shows for this movie
        List<Show> availableShows = new ArrayList<>();
        for (Show s : allShows) {
            if (s.getMovie().getTitle().equals(selectedMovie.getTitle())) {
                availableShows.add(s);
            }
        }
        if (availableShows.isEmpty()) {
            System.out.println("No shows for this movie.");
            return;
        }
        System.out.println("Shows for " + selectedMovie.getTitle() + ":");
        for (int i = 0; i < availableShows.size(); i++) {
            Show s = availableShows.get(i);
            System.out.println((i+1) + ". Screen-" + s.getScreen().getScreenNumber() + " at " + s.getStartTime());
        }
        System.out.print("Choose show: ");
        int showIdx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (showIdx < 0 || showIdx >= availableShows.size()) {
            System.out.println("Invalid show.");
            return;
        }
        Show selectedShow = availableShows.get(showIdx);

        // 3. Display seat layout
        selectedShow.displayLayout();

        // 4. Ask for seat numbers
        System.out.print("Enter seat numbers separated by commas (e.g., A1,B2): ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        List<String> seatNumbers = new ArrayList<>();
        for (String p : parts) {
            seatNumbers.add(p.trim().toUpperCase());
        }

        // 5. Ask for customer details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone: ");
        String phone = scanner.nextLine();
        Customer customer = new Customer(name, phone);

        // 6. Choose payment method
        System.out.println("Pay by: 1. UPI  2. Card  3. Cash");
        int payOpt = scanner.nextInt();
        scanner.nextLine();
        Payment payment;
        switch (payOpt) {
            case 1: payment = new UpiPayment(); break;
            case 2: payment = new CardPayment(); break;
            case 3: payment = new CashPayment(); break;
            default: System.out.println("Invalid payment option."); return;
        }

        // 7. Delegate to BookingService
        Booking booking = bookingService.bookTickets(selectedShow, customer, seatNumbers, payment);
        if (booking != null && booking.getStatus() == BookingStatus.Confirmed) {
            allBookings.add(booking);
        }
    }

    private static void cancelBooking() {
        if (allBookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }
        System.out.println("Your bookings:");
        for (int i = 0; i < allBookings.size(); i++) {
            Booking b = allBookings.get(i);
            System.out.println((i+1) + ". " + b.getBookingID() + " - " + b.getShow().getMovie().getTitle() +
                    " - " + b.getStatus());
        }
        System.out.print("Enter booking number to cancel: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();
        if (idx < 0 || idx >= allBookings.size()) {
            System.out.println("Invalid.");
            return;
        }
        Booking toCancel = allBookings.get(idx);
        bookingService.cancelBooking(toCancel);
    }
    private static void showMyBookings() {
        if (allBookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : allBookings) {
            String seatNumbers = b.getShowSeats().stream()
                    .map(ss -> ss.getSeat().getSeatNumber())
                    .collect(Collectors.joining(", "));
            System.out.println(b.getBookingID() + " : " + b.getShow().getMovie().getTitle() +
                    " - " + b.getStatus() + " - Seats: " + seatNumbers);
        }
    }
}
