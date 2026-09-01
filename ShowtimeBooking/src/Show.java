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
        return null;
    }
}
