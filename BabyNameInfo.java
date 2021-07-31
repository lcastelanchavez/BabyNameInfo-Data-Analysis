package hw5; 
import java.util.ArrayList;

public class BabyNameInfo {
    private String BabyName = "";           //initialize member variables with default values 
    private char gender = '\n';
    private ArrayList < Integer [] > num_of_births_vs_year;


    //create BabyNameInfo constructor 
    public BabyNameInfo(String _BabyName,char _gender, ArrayList<Integer[]> _nbirths_per_year)
    {
        //initialize member variables
        this.BabyName = _BabyName;
        this.gender = _gender;
        this.num_of_births_vs_year = _nbirths_per_year;  
        
    }

    //returns the total number of births in all recorded years of BabyNameInfo object 
    public int sumAll() {
        int sum = 0; 
        for(int i=0; i < num_of_births_vs_year.size(); i++) //go through all years and add birth numbers 
        {
            sum += num_of_births_vs_year.get(i)[1]; //get the number of births from each record 
            //and add to total 
        }

        return sum; 
    }


    //adds new record to existing BabyNameInfo object 
    public void add(int year, int num_births, int rank) {
        //if a babyname has been recorded, add new record to BabyNameInfo object's ArrayList  
        if(BabyName != "") {
            num_of_births_vs_year.add(new Integer[] {year, num_births, rank}); 
        }
    }

    //overriding how BabyNameInfo object is printed 
    @Override
    public String toString() 
    {
        String total = ""; 
        String yrHeader = "Year"; 
        String birthHeader = "Number of Births"; 
        String rankHeader = "Rank"; 
        String nameHeader = "Baby name";
        //String hyphen = "-".repeat(40);

        //line one of output showing name and gender of selected babyname 
        total = String.format("%-10s: %-10s %-10s \n", nameHeader, BabyName, "(" + gender + ")"); 

        //line two of output headers
        total += String.format("%-10s %-10s %-10s \n", yrHeader, birthHeader, rankHeader); 
        
        //data from table 

        for(int i = 0; i < num_of_births_vs_year.size(); i++) //output all of the years + birth numbers 
        {
            int currYear = num_of_births_vs_year.get(i)[0];
            int currBirthsNum = num_of_births_vs_year.get(i)[1];
            int currRank = num_of_births_vs_year.get(i)[2];
            total += String.format("%-10s %12s %8s \n",currYear,currBirthsNum,currRank); 
        }
        
        String hyphen = "-".repeat(40); 
        total = total + hyphen + "\n"; 
        total += String.format("Total = %-20s", this.sumAll()); //adds the final birth numbers

        return total; 

    }


    /*
    public static void main(String[] args) { 
    
    int year1 = 2001;
    int rank1 = 1;
    int birthnums1 = 10000; 
    int year2 = 2002;
    int rank2 = 2;
    int birthnums2= 190; 

    ArrayList < Integer [] > n = new ArrayList <Integer[]>();

    n.add(new Integer[] {year1, birthnums1, rank1});  

    n.add(new Integer[] {year2, birthnums2, rank2});  

    //System.out.println(n); 

    BabyNameInfo test = new BabyNameInfo("leslie", 'F',n);
    System.out.println(test); 
    
    }
    **/ 


}

