import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
	
    public static String[][] readmovie(){
        String allmovie = utility.readContent("movie.txt");
        String [] movie = allmovie.split("\n");
        String [][] details = new String [movie.length][7];
        int count=0;
        for(int i=0;i<movie.length;i++){
            String [] moviename= movie[i].split("\\|");
            if(moviename[6].equals("End of Showing")){
            }else{
               for(int k=0;k<7;k++){
                details[count][k]=moviename[k];
                }
                count++;
            }   
        }
        for(int z=count;z<movie.length;z++){
            details[z]=null;
        }
        return details;
    }
    
	public static void listMovies() {
		String [][] list= readmovie();
        
        for(int i=0;i<list.length;i++){
            if(list[i]==null){
                break;
            } 
            System.out.println(list[i][0]+"  : "+list[i][6]);
        }
	}
    
    /* public static void viewMovieDetails(){
        String [][] list= readmovie();
        
        for(int i=0;i<list.length;i++){
            if(list[i][6].equals("End of Showing")){
                continue;
            }
            int k=i+1;
            System.out.println("("+k+") "+list[i][0]);
        }
    } */
}
