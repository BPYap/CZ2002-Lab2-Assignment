import java.util.*;
import java.io.* ;

public class utility
{
    public static void printBorder()
    {
        System.out.println("=============================================");
    }
    
    public static void printline()
    {
        System.out.println("---------------------------------------------");
    }
    
    public static void print_title_row(String titles, String widths)
    // separate title and width of each column with comma 
    // Example: print_title_row("Movie title, Age rating, Status", "10, 20, 10")
    {
        String[] title_array = titles.split(",");
        String[] width_array = widths.split(",");
        
        String title_border = "+===+";
        for (int i = 0; i < title_array.length; i++)
        {
            for(int j = 0; j < Integer.parseInt(width_array[i]); j++)
            {
               title_border += "="; 
            }
            title_border += "+";
        }
        
        String format = "|   |";
        for (int i = 0; i < width_array.length; i++)
        {
            format += "%" + Integer.parseInt(width_array[i]) + "s|";
        }
        
        System.out.println(title_border);
        System.out.format(format + "\n", title_array);
        System.out.println(title_border);
        
    }
    
    public static void print_row(int index, String row, String widths)
    // include indexing
    {
        String[] temp = row.split(",");
        String[] row_attributes = new String[temp.length + 1];
        row_attributes[0] = Integer.toString(index);
        String[] width_array = widths.split(",");
        
        String border = "+---+";
        for (int i = 0; i < temp.length; i++)
        {
            if(i != 0)
            {
                row_attributes[i] = temp[i-1];
            }
            for(int j = 0; j < Integer.parseInt(width_array[i]); j++)
            {
               border += "-"; 
            }
            border += "+";
        }
        
        row_attributes[temp.length] = temp[temp.length - 1];
        
        String format = "|%3s|";
        for (int i = 0; i < width_array.length; i++)
        {
            format += "%" + Integer.parseInt(width_array[i]) + "s|";
        }
        
        System.out.format(format + "\n", row_attributes);
        System.out.println(border);
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
            System.out.println("Problem reading file. " + " " + e.getMessage());
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
            System.out.println("Problem reading file. " + e.getMessage());
            System.exit(0);
        }
        return "None";
    }

/*     public static String readContent(String file_name)
    // Return all records from file. Each record is separated by '\n'
    {
        try 
        {
            BufferedReader file = new BufferedReader(new FileReader(file_name));
            StringBuffer inputBuffer = new StringBuffer();
            
            String current_line = file.readLine();      
            //System.out.println(current_line);

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
    } */
    
    public static String[] readContent(String file_name)
    // Return all records from file in String array. 
    {
        try 
        {
            BufferedReader file = new BufferedReader(new FileReader(file_name));
            
            StringBuffer inputBuffer = new StringBuffer();
            
            String current_line = file.readLine();      
            //System.out.println(current_line);

            while (current_line != null) 
            {
                inputBuffer.append(current_line);
                inputBuffer.append("\n");
                current_line = file.readLine();
            }
            String file_content = inputBuffer.toString();
            file.close();
            return file_content.split("\n");
        } 
        catch (Exception e) 
        {
            System.out.println("Problem reading file. " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(0);
        }
        return null;
    }
}    

 
