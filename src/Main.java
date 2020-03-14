import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException{
        ArrayList<HashMap<String, Object>> test1 = readtxt();
        ArrayList<HashMap<String, Object>> test2 = readcsv();
        System.out.println(test1.size());
        System.out.println(test2.size());
        System.out.println(sumGpa(test1));
        System.out.println(sumGpa(test2));
        System.out.println(sumLen(test1, "Address.Street"));
        System.out.println(sumLen(test2, "Address.Street"));
    }

    public static ArrayList<HashMap<String, Object>> readtxt() throws IOException {
        //BufferedReader reader = new BufferedReader(new FileReader("src/Student.txt")); //Creates a buffered
       // reader to read in the file
        URL url = new URL("http://mas.lvc.edu/cds280/Student.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        String line = reader.readLine(); //Reads first line of file
        String[] headers = line.split("\t"); //Splits headers into separate strings and stores in array
        String line1 = reader.readLine();
        while(line1 != null) {
            HashMap<String,Object> map = new HashMap<String,Object>(); //Create a map for each line of the file
            String[] nextLine = line1.split("\t"); //Split line into separate strings
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals("GPA")) {
                    map.put("GPA", Double.valueOf(nextLine[i])); //Make GPA's a numeric value in map
                }
                else {
                    map.put(headers[i], nextLine[i]); //Add every other type as a String
                }
            }
            list.add(map);
            line1 = reader.readLine(); //Read the next line of the file
        }
        reader.close();
        return list;
    }

    public static ArrayList<HashMap<String, Object>> readcsv() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Student.csv")); //Creates a buffered
        //reader to read in the file
        ArrayList list = new ArrayList();
        String line = reader.readLine(); //Reads first line of file
        String[] headers = line.split(","); //Splits headers into separate strings and stores in array
        String line1 = reader.readLine();
        while(line1 != null) {
            HashMap<String,Object> map = new HashMap<String,Object>(); //Create a map for each line of the file
            String[] nextLine = line1.split(","); //Split line into separate strings
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals("GPA")) {
                    map.put("GPA", Double.valueOf(nextLine[i])); //Make GPA's a numeric value in map
                }
                else {
                    map.put(headers[i], nextLine[i]); //Add every other type as a String
                }
            }
            line1 = reader.readLine(); //Read the next line of the file
            list.add(map);
        }
        reader.close();
        return list;
    }

    public static double sumGpa(ArrayList<HashMap<String, Object>> q) {
        double ret = 0;
        for (int i = 0; i < q.size(); i++) {
            ret +=(double) q.get(i).get("GPA"); //Loop through the list and get the value for GPA and
            // add it to the return value
        }
        return ret;
    }

    public static int sumLen(ArrayList<HashMap<String, Object>> list, String name) {
        int ret = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get(name).toString().contains("'") || list.get(i).get(name).toString().contains("\"")) {
                ret -= 2; // Ignore the quotes in the Address.Street column
            }
           ret += list.get(i).get(name).toString().length(); //Loop through the list and get the value for the
            // parameter column and add it to the return value
        }
        return ret;
    }
}
