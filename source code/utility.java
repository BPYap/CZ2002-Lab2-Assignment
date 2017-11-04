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
            while (current_lineline != null) 
            {
                inputBuffer.append(current_line);
                inputBuffer.append('\n');
                current_line = file.readline();
            }
            String file_content = inputBuffer.toString();
            file.close();

            System.out.println("Content of " + file_name + ": " + file_content); // debug

            file_content = file_content.replace(old_record, new_record); 

            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(file_name);
            fileOut.write(file_content.getBytes());
            fileOut.close();

        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + file_name);
        }
    }    
}    

 
