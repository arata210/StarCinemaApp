package moe.arata210.starcinema.ui.home;

public class Movie {
    private String title;
    private String director;
    private String mainActors;
    private String rating;
    private int poster;

    public Movie(String title, String director, String mainActors, String rating, int poster) {
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
        this.rating = rating;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getMainActors() {
        return mainActors;
    }

    public String getRating() {
        return rating;
    }

    public int getPoster() {
        return poster;
    }
}
