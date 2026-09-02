import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private List<ShowSeat>showSeats;

    public Show(Movie movie , Screen screen , LocalDateTime startTime)
    {
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = new ArrayList<>();
        //create a ShowSeat for every physical seat in the screen
        for(Seat seat : screen.getSeats())
        {
            ShowSeat showSeat = new ShowSeat(seat);
            showSeats.add(showSeat);
        }
    }

    public Movie getMovie()
    {
        return movie;
    }
    public Screen getScreen()
    {
        return screen;
    }
    public LocalDateTime getStartTime()
    {
        return startTime;
    }
    public List<ShowSeat>getShowSeats(){
        return showSeats;
    }
    public ShowSeat findShowSeat(String seatNumber){
        for(ShowSeat ss : showSeats)
        {
            if (ss.getSeat().getSeatNumber().equals(seatNumber))
            {
                return ss;
            }
        }
        return null;
    }

    public void  displayLayout()
    {
        System.out.println("Seat layout (" + screen.getScreenNumber() + "):");
        for (ShowSeat ss :showSeats)
        {
            String marker = ss.isAvailable() ? "[ ]" : "[X]";
            System.out.println(ss.getSeat().getSeatNumber() + marker + " " );
            //Line break after every 5 seats to make it look better
            if (ss.getSeat().getSeatNumber().length() == 2 &&
            ss.getSeat().getSeatNumber().charAt(1) == '5') {
                System.out.println();
            }
        }
        System.out.println();

    }
}
