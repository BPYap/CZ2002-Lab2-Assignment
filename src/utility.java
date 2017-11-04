import java.util.*;
import java.io.* ;

public class utility
{
    public static void printBorder()
    {
        System.out.println("================================");
    }
    
    public static void updateFile(String file_name, String old_record, String new_record) 
    // replace old record in file with new record. Recommend to use readLine(file_name, keyword) to find old record
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

            // write the new String with the replaced line OVER the same file
            BufferedWriter bwStream = new BufferedWriter(new FileWriter(file_name));
            PrintWriter    fileOut = new PrintWriter(bwStream);
            fileOut.write(file_content);
            fileOut.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + file_name + " " + e.getMessage());
            System.exit(0);
        }
    }

    public static void addRecord(String filename, String record)
    // append new record to file
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
            System.out.println( "Problem writing file. " + filename + " " + e.getMessage());
            System.exit(0);
        }
    }
    
    public static String readLine(String file_name, String keyword)
    // read a record from file using specified unique keywords separated by comma ','
    {
        try 
        {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(file_name));
        
            String current_line = file.readLine();
            
            String[] keywords = keyword.split(",");
            int matched = 0; 
            while (current_line != null) 
            {
                for (int i = 0; i < keywords.length; i++)
                {
                    if (current_line.toLowerCase().contains(keywords[i].toLowerCase()))
                    {
                        matched++;
                    }
                    else
                    {
                        current_line = file.readLine();
                        break;
                    }
                    
                    if (matched == keywords.length)
                    {
                        file.close();
                        return current_line;
                    }
                    else
                    {
                        current_line = file.readLine();
                    }
                        
                }
            }
            file.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + file_name + " " + e.getMessage());
            System.exit(0);
        }
        return "None";
    }

    public static String readContent(String file_name)
    // Return all records from file. Each record is separated by '\n'
    {
        try 
        {
            BufferedReader file = new BufferedReader(new FileReader(file_name));
            StringBuffer inputBuffer = new StringBuffer();
            
            String current_line = file.readLine();      

            while (current_line != null) 
            {
                inputBuffer.append(current_line);
                inputBuffer.append("\n");
                current_line = file.readLine();
            }
            String file_content = inputBuffer.toString();
            file.close();
            return file_content;
        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + file_name + " " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}    

 