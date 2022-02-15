package day02;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();

    private void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> movieContainingActor(String name) {
        return movies.stream()
                .filter(movie -> movie.getActors().contains(name))
                .toList();
    }

    public long findLengthOfLongestMovie() {
        return movies.stream()
                .mapToInt(Movie::getLength)
                .max().orElseThrow(() -> new IllegalStateException("Empty list!"));
    }


}