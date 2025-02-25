package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = new ArrayList<>(genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    //Liste mit vordefiniertten Filmen
    //Eine Liste von Movie-Objekten

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();

        // Existing examples
        Movie rambo = new Movie(
                "Rambo",
                "A movie about a veteran going havoc",
                List.of(Genre.ACTION, Genre.DRAMA)
        );
        Movie titanic = new Movie(
                "Titanic",
                "A movie about a ship sinking",
                List.of(Genre.DRAMA, Genre.ROMANCE)
        );
        movies.add(rambo);
        movies.add(titanic);

        // Add more movies
        Movie inception = new Movie(
                "Inception",
                "A mind-bending thriller about dream infiltration",
                List.of(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER)
        );
        Movie godfather = new Movie(
                "The Godfather",
                "The story of the Corleone mafia family",
                List.of(Genre.CRIME, Genre.DRAMA)
        );
        Movie darkKnight = new Movie(
                "The Dark Knight",
                "Batman faces the Joker in Gotham",
                List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)
        );
        Movie pulpFiction = new Movie(
                "Pulp Fiction",
                "Non-linear stories of crime in Los Angeles",
                List.of(Genre.CRIME, Genre.DRAMA)
        );
        Movie forrestGump = new Movie(
                "Forrest Gump",
                "A simple man experiences historical events",
                List.of(Genre.COMEDY, Genre.DRAMA, Genre.ROMANCE)
        );
        Movie lotrFellowship = new Movie(
                "The Lord of the Rings: The Fellowship of the Ring",
                "A hobbit sets out to destroy an ancient ring",
                List.of(Genre.ADVENTURE, Genre.FANTASY)
        );
        Movie theMatrix = new Movie(
                "The Matrix",
                "A programmer learns reality is a simulation",
                List.of(Genre.SCIENCE_FICTION, Genre.ACTION)
        );
        Movie starWars = new Movie(
                "Star Wars: A New Hope",
                "A farm boy joins a galactic rebellion",
                List.of(Genre.SCIENCE_FICTION, Genre.ADVENTURE)
        );
        Movie jurassicPark = new Movie(
                "Jurassic Park",
                "Dinosaurs are cloned in a theme park",
                List.of(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.ADVENTURE)
        );
        Movie backToFuture = new Movie(
                "Back to the Future",
                "A teenager travels back in time to 1955",
                List.of(Genre.SCIENCE_FICTION, Genre.COMEDY, Genre.ADVENTURE)
        );
        Movie avengersEndgame = new Movie(
                "Avengers: Endgame",
                "Marvel heroes unite to reverse a cosmic threat",
                List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)
        );
        Movie joker = new Movie(
                "Joker",
                "A mentally troubled comedian descends into madness",
                List.of(Genre.CRIME, Genre.DRAMA)
        );
        Movie gladiator = new Movie(
                "Gladiator",
                "A Roman general seeks revenge",
                List.of(Genre.ACTION, Genre.DRAMA)
        );
        Movie toyStory = new Movie(
                "Toy Story",
                "Toys come to life when humans aren't around",
                List.of(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)
        );
        Movie shrek = new Movie(
                "Shrek",
                "An ogre rescues a princess from a dragon",
                List.of(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)
        );
        Movie interstellar = new Movie(
                "Interstellar",
                "Astronauts travel through a wormhole to find a new home",
                List.of(Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.DRAMA)
        );
        Movie shawshank = new Movie(
                "The Shawshank Redemption",
                "Two imprisoned men bond over years",
                List.of(Genre.DRAMA)
        );
        Movie lionKing = new Movie(
                "The Lion King",
                "A young lion prince flees his kingdom",
                List.of(Genre.ANIMATION, Genre.DRAMA, Genre.FAMILY)
        );
        Movie fightClub = new Movie(
                "Fight Club",
                "An insomniac and a soap salesman start a fight club",
                List.of(Genre.DRAMA)
        );
        Movie savingPrivateRyan = new Movie(
                "Saving Private Ryan",
                "A group of soldiers search for a paratrooper in WWII",
                List.of(Genre.ACTION, Genre.DRAMA, Genre.WAR)
        );

        // Add them all to the list
        movies.add(inception);
        movies.add(godfather);
        movies.add(darkKnight);
        movies.add(pulpFiction);
        movies.add(forrestGump);
        movies.add(lotrFellowship);
        movies.add(theMatrix);
        movies.add(starWars);
        movies.add(jurassicPark);
        movies.add(backToFuture);
        movies.add(avengersEndgame);
        movies.add(joker);
        movies.add(gladiator);
        movies.add(toyStory);
        movies.add(shrek);
        movies.add(interstellar);
        movies.add(shawshank);
        movies.add(lionKing);
        movies.add(fightClub);
        movies.add(savingPrivateRyan);

        return movies;
    }
    public boolean matches(String query, Genre genre){
        boolean matchesQuery = (query == null || query.isEmpty()) ||
                title.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) ||
                description.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT));

        boolean matchesGenre = (genre == null) || genres.contains(genre);
        return matchesQuery && matchesGenre;
    }

}
