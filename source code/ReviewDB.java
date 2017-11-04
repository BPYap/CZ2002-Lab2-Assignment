

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReviewDB {
	public static final String SEPARATOR = "|";

    // an example of reading
	public static ArrayList readReview(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store  data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	

				String  movie_title = star.nextToken().trim();	// first token
				String  reviewer =  star.nextToken().trim();
				String comments = star.nextToken().trim();
				int rating = Integer.parseInt(star.nextToken().trim());
				
				
				
				// create Professor object from file data
				Review rov = new Review(movie_title,reviewer,comments,rating);
				// add to Professors list
				alr.add(rov) ;
			}
			return alr ;
	}

  // an example of saving
        public static void saveReview(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store  data

        for (int i = 0 ; i < al.size() ; i++) {
				Review rov = (Review)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(rov.getMovieTitle().trim());
				st.append(SEPARATOR);
				st.append(rov.getReviewer().trim());
				st.append(SEPARATOR);
				st.append(rov.getComments().trim());
				st.append(SEPARATOR);
				st.append(rov.getRating());
				
				alw.add(st.toString()) ;
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

public static void main(String[] aArgs)  {
    	ReviewDB DB = new ReviewDB();
    	String filename = "review.txt" ;
		try {
			// read file containing Professor records.
			ArrayList al = ReviewDB.readReview(filename) ;
			int h = 0;
			int j = 0;
			
			for (int i = 0 ; i < al.size() ; i++) {
				 Review rov = (Review)al.get(i);
				
				if(rov.getMovieTitle().equals("Fifty shades of freed")) {
					System.out.println( rov.toString() );
					
					h += rov.getRating();
					j++;
					
				}
				
		}
		if(h != 0) {
			System.out.println("overall rating :"+ (h*1.0/j*1.0));
		}
			
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }
}




