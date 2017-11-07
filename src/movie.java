public class movie {
    private String movie_title ;
    private String genre ;
    private String synopsis ;
    private String director;
    private String cast;
    private String age_rating;
    private String status;
    
    public movie(String a, String b,String c,String d,String e,String f,String g )  {
         movie_title = a ;
         genre = b ;
         synopsis = c;
         director = d;
         cast = e;
         age_rating = f;
         status = g;
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
        return String.format("Movie Title is "+ movie_title+
                "\nGenre: "+genre +
                "\nSynopis: "+synopsis +
                "\nDirector:" + director +
                "\nCast: " + cast +
                "\n Age Rating:"+ age_rating +
                "\n Status :"+ status +
                "\n--------------------------------------------------------\n");
    }
    public String setStatus(String newstatus) {
        return status = newstatus;
    }
    
}


