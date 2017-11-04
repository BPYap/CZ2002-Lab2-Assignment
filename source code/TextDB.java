package movie;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TextDB {
	public static final String SEPARATOR = "|";

    // an example of reading
	public static ArrayList readmovies(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	

				String  movie_title = star.nextToken().trim();	// first token
				String  genre =  star.nextToken().trim();
				String synopsis = star.nextToken().trim();
				String director = star.nextToken().trim();
				String cast = star.nextToken().trim();
				String age_rating = star.nextToken().trim();
				String status = star.nextToken().trim();
				
				
				
				// create Professor object from file data
				movie mov = new movie(movie_title, genre,synopsis,director,cast,age_rating,status);
				// add to Professors list
				alr.add(mov) ;
			}
			return alr ;
	}

  // an example of saving
        public static void savemovies(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store  data

        for (int i = 0 ; i < al.size() ; i++) {
				movie mov = (movie)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(mov.getMovieTitle().trim());
				st.append(SEPARATOR);
				st.append(mov.getGenre().trim());
				st.append(SEPARATOR);
				st.append(mov.getSynopsis().trim());
				st.append(SEPARATOR);
				st.append(mov.getDirector().trim());
				st.append(SEPARATOR);
				st.append(mov.getCast().trim());
				st.append(SEPARATOR);
				st.append(mov.getAgeRating().trim());
				st.append(SEPARATOR);
				st.append(mov.getStatus().trim());
				st.append(SEPARATOR);
			}
			write(filename,alw);
	}

  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }
  private static Scanner x;
  public static void update(String filename,String movieTitle,String newStatus) throws IOException {
	   String tempFile = ("temp.txt");
	   File oldFile = new File(filename);
	   File newFile = new File(tempFile);
	    String  movie_title = "";	// first token
		String  genre =  "";
		String synopsis = "";
		String director = "";
		String cast = "";
		String age_rating = "";
		String status = "";
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner x = new Scanner(new File(filename));
			x.useDelimiter("[|\n]");
			while(x.hasNext())
			{
				movie_title = x.next();	// first token
			     genre =  x.next();
				 synopsis = x.next();
				 director = x.next();
				 cast = x.next();
				 age_rating = x.next();
				 status = x.next();
				 if(movie_title.equals(movieTitle))
				 {
					 pw.println(movie_title +"|" + genre + "|" + synopsis + "|" + director + "|" + cast + "|" + age_rating +"|" + newStatus );
					 
				 }
				 else
				 {
					 pw.println(movie_title +"|" + genre + "|" + synopsis + "|" + director + "|" + cast + "|" + age_rating +"|" + status );
			     }
		   }
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filename);
			newFile.renameTo(dump);
		}
		catch(Exception e) {
			System.out.println("Error");
		}
			
	   
	}
public static void main(String[] aArgs)  {
    	TextDB txtDB = new TextDB();
    	String filename = "movie.txt" ;
		try {
			
			
			// read file containing Professor records.
			ArrayList al = TextDB.readmovies(filename) ;
			
			for (int i = 0 ; i < al.size() ; i++) {
					movie mov = (movie)al.get(i);
					
					if(mov.getStatus().equals("NowShowing")) {
						
						System.out.println( mov.toString() );
					}
					
			}
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }
}