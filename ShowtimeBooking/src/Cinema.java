import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private List<Screen> screens;
    private List<Movie> movies;

    public Cinema(String name)
    {
        this.name = name;
        this.screens = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    public List<Screen> getScreens() {
        return screens;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public String getName() {
        return name;
    }
}

