import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {

    public static String readDigits(String file)

    {
        StringBuilder input=new StringBuilder();

        try (FileReader reader=new FileReader(file))
        {
            Scanner scan = new Scanner(reader);
            while(scan.hasNext()) {
                String t = scan.next();
                if (t.matches("[+-]?\\d+([,.]\\d+)?([\\W]{0,3}$)"))
                    input.append(t.replaceFirst("([+-]?\\d+([,.]\\d+)?)([\\W]{0,3}$)", "$1")).append(" ");
            }

        }catch (IOException ex){;}

        return input.toString();

    }


    public static String readUnics(String file)
    {
        Map<String, Integer> dictionary=new HashMap<String, Integer>();

        try (FileReader reader=new FileReader(file))
        {
            Scanner scan = new Scanner(reader);
            while(scan.hasNext()) {
               String t = scan.next();
                if (t.matches("(\\w+)([\\W]{0,3}$)"))
                    t=(t.replaceFirst("(\\w+)([\\W]{0,3}$)", "$1"));
                if(dictionary.containsKey(t)) dictionary.replace(t, 2);
                    else dictionary.put(t,1);

            }

        }catch (IOException ex){;}

        StringBuilder input=new StringBuilder();
        for(var x: dictionary.entrySet()) if (x.getValue()<2) input.append(x.getKey()+"\n");
        if(input.length()>0)input.replace(input.length()-1,input.length(),"");

        return input.toString();
    }

}
