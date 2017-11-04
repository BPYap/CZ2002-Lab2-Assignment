import java.util.*;
import java.io.* ;

public class utility
{
    public static void updateFile(String file_name, String old_record, String new_record) 
    {    
        try 
        {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(file_name));
            StringBuffer inputBuffer = new StringBuffer();
        
            String current_line = file.readLine();
            while (current_line != null) 
            {
                inputBuffer.append(current_line);
                inputBuffer.append('\n');
                current_line = file.readLine();
            }
            String file_content = inputBuffer.toString();
            file.close();

            file_content = file_content.replace(old_record, new_record); 
            System.out.println("Content of " + file_name + ": " + file_content); // debug

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(file_name);
            fileOut.write(file_content.getBytes());
            fileOut.close();

        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + file_name);
            System.exit(0);
        }
    }

    public static void addRecord(String filename, String record)
    {
        try 
        {
            FileWriter fwStream = new FileWriter(filename, true);
            BufferedWriter bwStream = new BufferedWriter(fwStream);
            PrintWriter pwStream = new PrintWriter(bwStream);
            pwStream.println(record);
            pwStream.close();
        }
        catch(Exception e)
        {
            System.out.println( "Problem writing file. " + filename + " " + e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        updateFile("test.txt", "line 1", "line1");
        updateFile("test.txt", "line 2", "line2");
    }
}    

 
