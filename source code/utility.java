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
            String newLineChar = System.getProperty("line.separator");
            while (current_line != null) 
            {
                inputBuffer.append(current_line.replace("\n", ""));
                inputBuffer.append(newLineChar);
                current_line = file.readLine();
            }
            String file_content = inputBuffer.toString();
            file.close();

            file_content = file_content.replace(old_record, new_record); 
            System.out.print("Content  : " + file_content); // debug

            // write the new String with the replaced line OVER the same file
            BufferedWriter bwStream = new BufferedWriter(new FileWriter(file_name));
            PrintWriter    fileOut = new PrintWriter(bwStream);
            fileOut.write(file_content);
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
        updateFile("test.txt", "line 3", "line1");
    }
}    

 
