package hw5;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;


public class BabyNameAnalysis {

    //This method returns a result of type HashMap<String,BabyNameInfo>
    //constructed from data from URLS
    public static HashMap <String ,BabyNameInfo> createHashMap() {

        //create arraylist to store URLS 
        ArrayList<URL> urls = new ArrayList<URL>(); 
        //create hashmap 
        HashMap <String , BabyNameInfo > hm = new HashMap <String, BabyNameInfo>();

        try {
            //read URLs and add them to ArrayList of URL objects
            URL url1;
            URL url2;
            URL url3;
            URL url4;
            URL url5; 
            URL url6;
            URL url7;
            URL url8; 
            URL url9; 
            URL url10; 
            url1 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2011.txt"); 
            url2 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2012.txt"); 
            url3 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2013.txt"); 
            url4 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2014.txt"); 
            url5 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2015.txt");
            url6 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2016.txt");
            url7 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2017.txt"); 
            url8 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2018.txt"); 
            url9 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2019.txt"); 
            url10 = new URL("https://www.math.ucla.edu/~minhrose/baby_name_2020.txt"); 

            urls.add(url1); 
            urls.add(url2); 
            urls.add(url3); 
            urls.add(url4); 
            urls.add(url5); 
            urls.add(url6); 
            urls.add(url7); 
            urls.add(url8); 
            urls.add(url9); 
            urls.add(url10); 


            //go through each URL and extract data
            for(int i=0; i < urls.size(); i++)
            {
                //open BufferReader stream to read data per line
                //BufferedReader in1 = new BufferedReader(new InputStream((urls.get(i)).openStream())); 

                BufferedReader in = new BufferedReader(new InputStreamReader((urls.get(i)).openStream())); 
                int y = 11 + i; 
                String year = "20" + y; 
                int currYear = Integer.parseInt(year); //construct the year based on index of curr URL 
                String inputLine; 

                while ((inputLine = in.readLine()) != null) {   //read each line 
                    //Scanner currLine = new Scanner(inputLine); 
                    String[] strArr = inputLine.split("\\s+"); //split each line by spaces 
                    //array of line 
                        //0         1       2           3           4
                    // |  rank |  mname | mbirthnum  |  fname |  fbirthnum |   

                    int ranking, mbirthNums; 
                    //char gend; 
                    String mname; 
                    
                    //ranking= currLine.nextInt();  
                    ranking = Integer.parseInt(strArr[0]); //get the rank of person
                    //mname = currLine.next(); 
                    mname = strArr[1];
                    String mbirthnum; 

                    //get the birth number of males 
                    if(strArr[2].indexOf(",") != -1) {  //if there is a comma, create number from both sides of comma
                        String[] strArrNum = strArr[2].split(",");
                        mbirthnum = strArrNum[0] + strArrNum[1]; 
            
                    }
                    else {
                        mbirthnum = strArr[2]; //else if no comma make it the number
                    }
                    mbirthNums = Integer.parseInt(mbirthnum);   //convert string to number

                     
                    //look through the HashMap for male name

                    if(hm.get(mname) !=null) //if male name exists already
                    {
                        //add info to BabyNameInfo object's ArrayList 
                        //using BabyNameInfo's member function add
                        hm.get(mname).add(currYear, mbirthNums, ranking); 
    
                    }
                    else {  //if does not exist, create new BabyNameInfo object and add it
                            //to HashMap hm 
                        Integer[] mInfo = {currYear, mbirthNums, ranking}; 
                        ArrayList<Integer[]> mnumBirthsxYr = new ArrayList <Integer[]>(); 
                        mnumBirthsxYr.add(mInfo); 
                        BabyNameInfo male = new BabyNameInfo(mname, 'M', mnumBirthsxYr);
                        hm.put(mname, male); //add babyNameInfo object to hashmap 
                    }

        
                }

            }

        }

        catch(Exception e) {    //catch any exceptions 
            System.out.println("URLs did not open yikes"); 
            e.printStackTrace();
        }
        finally {
            
            System.out.println("it worked"); 
            
        }
        return hm;  //return completed hashmap
    }

    //implement sorting method to order hashmap 
    public static class BabyNameOrdering implements Comparator<Map.Entry <String , BabyNameInfo>>{
        @Override
        public int compare(java.util.Map.Entry<String, BabyNameInfo> o1, java.util.Map.Entry<String, BabyNameInfo> o2) {
            // TODO Auto-generated method 
            int o1Births = o1.getValue().sumAll();
            int o2Births = o2.getValue().sumAll(); 

            if     (o1Births < o2Births) return 1; 
            else if(o1Births > o2Births) return -1; 
        
            return 0; 
            
        }
    }


    public static void main ( String args []){
        HashMap <String , BabyNameInfo > hm = createHashMap ();
        //System.out.println(hm.get("Jacob"));
        List <Map . Entry <String , BabyNameInfo>> bList = new ArrayList <>(hm.entrySet());
        Collections.sort( bList,new BabyNameOrdering()); 
        // output the most 5 popular baby male name

        for(int i=0;i<5;i++){

            //bList.get(i) => returns a tuple with string and BabyNameInfo object
            //getValue gets the BabyNameInfo object
            System.out.println( bList.get(i).getValue() );

        }  

    }

}
