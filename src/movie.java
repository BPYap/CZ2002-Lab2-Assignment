public class Movie {
    private String movie_title ;
    private String genre ;
    private String synopsis ;
    private String director;
    private String cast;
    private String age_rating;
    private String status;
    
    public Movie(String a, String b,String c,String d,String e,String f,String g ){
         movie_title = a ;
         genre = b ;
         synopsis = c;
         director = d;
         cast = e;
         age_rating = f;
         status = g;
    }
    
    public Movie(String record)
    {
        String [] attributes = record.split("\\|");
        movie_title = attributes[0];
        genre = attributes[1];
        synopsis = attributes[2];
        director = attributes[3];
        cast = attributes[4];
        age_rating = attributes[5];
        status = attributes[6];
    }
    public String getMovieTitle(){return  movie_title; }
    public String getGenre() { return genre ; }
    public String getSynopsis() { return synopsis ; }
    public String getDirector() { return director  ; }
    public String getCast() { return cast ; }
    public String getAgeRating() { return age_rating ; }
    public String getStatus() { return status ; }
    @Override
    public String toString() {
        return movie_title + "|" + genre + "|" + synopsis + "|" + director + "|" + cast + "|" + age_rating + "|" + status;
    }
    public String setStatus(String newstatus) {
        return status = newstatus;
    }
    
}


