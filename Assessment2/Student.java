/**
 * Assignment 2 - In this assignment we are going to read the given file 
 * Calculate the total of marks of the students and print them with 
 * unit name,student name, student ID, Assignment marks and total marks.
 * In  the next function we're going to take the threshold value from the 
 * user and print the student marks which  are less than the threshold value.
 * In other function we're going print the Top 10 highest and lowest student marks
 * from the given file.
 * At last, there's a menu by which user can print the desired function from 
 * all function.
 *
 * @author (Ehtesham Ali Syed - 24025045)
 * @version (8-04-2022)
 */
import java.io.File; // This import is used for reading the given file
import java.io.FileNotFoundException; // This import is used if the file is not found 
import java.util.Scanner; //This import Scannner is used to collect the input
import java.util.*;
public class Student
{
    private ArrayList<String> lastNames;   // It is used to store the last name of the students  
    private ArrayList<String> firstNames;  // It is used to store the first name of the students
    private ArrayList<Integer> studentId;  // It is used to store Student Id  
    private ArrayList<Double> assignment1; // It is used to store the assignment1 marks of the students
    private ArrayList<Double> assignment2; // It is used to store the assignment2 marks of the students
    private ArrayList<Double> assignment3; // It is used to store the assignment3 marks of the students
    private ArrayList<Double> totalmarks;  // It is used to store the total marks of the students 
    private String line;
    private String firstLine;
    private String[] tokens;
    /**
     * Constructor for objects of class Student
     */
    public Student()
    {
        lastNames = new ArrayList<String>();
        firstNames = new ArrayList<String>();
        studentId = new ArrayList<Integer>();
        assignment1 = new ArrayList<Double>();
        assignment2 = new ArrayList<Double>();
        assignment3 = new ArrayList<Double>();
        totalmarks = new ArrayList<Double>();
    }

    public void readFile() // This method is used to read the given file.
    {
        int countLines=0;
        try{
            File myFile=new File("student.csv");
            Scanner myScanner=new Scanner(myFile);
            firstLine = myScanner.nextLine();
            myScanner.nextLine();
            while(myScanner.hasNextLine()){
                line=myScanner.nextLine();
                ++countLines;
                if(countLines>=1)
                {
                    tokens = line.split(",");
                    setlastNames();
                    setfirstNames();
                    setstudentId();
                    setassignment1();
                    setassignment2();
                    setassignment3();
                }
            }
            double marks; // This variable is declared for calculating the assignent marks
            for(int i=0 ; i < assignment1.size(); i++)  
            {
                marks = assignment1.get(i) + assignment2.get(i) + assignment3.get(i); // Here we're adding the assignment marks
                totalmarks.add(marks);
            }
            myScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found"); // This will display if the file is not found
            e.printStackTrace();
        }
    }

    /**
     * Method setLastNames
     * this method adds all the first items from the file
     * to the lastNames collection
     */
    public void setlastNames()
    {
        lastNames.add(tokens[0]);
    }

    /**
     * Method setFirstNames
     * this method adds all the second items from the file
     * to the firstNames collection
     */
    public void setfirstNames()
    {
        firstNames.add(tokens[1]);
    }

    /**
     * Method setStudentID
     * this method adds all the student ID from the file
     * to the studentID collection
     */
    public void setstudentId()
    {
        studentId.add(Integer.valueOf(tokens[2]));
    }

    /**
     * Method setAssignment1
     * this method adds all the Assignment1 marks from the file
     * to the assignment1 collection
     */
    public void setassignment1()
    {
        if(tokens.length<4)
        {
            assignment1.add(0.0);
            return;
        }
        if(tokens[3].equals("")) // Here If in the file assignment marks is not there thn it will take it as '0.0'
            tokens[3] = "0.0";
        assignment1.add(Double.valueOf(tokens[3]));
    }

    /**
     * Method setAssignment2
     * this method adds all the Assignment2 marks from the file
     * to the assignment2 collection
     */
    public void setassignment2()
    {
        if(tokens.length<5)
        {
            assignment2.add(0.0);
            return;
        }
        if(tokens[4].equals("")) // Here If in the file assignment marks is not there thn it will take it as '0.0'
            tokens[4] = "0.0";
        assignment2.add(Double.valueOf(tokens[4]));
    }

    /**
     * Method setAssignment3
     * this method adds all the Assignment3 marks from the file
     * to the assignment3 collection
     */
    public void setassignment3()
    {
        if(tokens.length<6)
        {
            assignment3.add(0.0);
            return;
        }
        if(tokens[5].equals("")) // Here If in the file assignment marks is not there thn it will take it as '0.0'
            tokens[5] = "0.0";
        assignment3.add(Double.valueOf(tokens[5]));
    }

    /**
     * Method display
     * this method displays all details of the student inncluding 
     * student name, student ID, assignment marks and total marks
     */
    public void display()
    {
        System.out.println(firstLine);
        for(int i = 0 ; i < lastNames.size() ; i++){
            System.out.println("Name : "+lastNames.get(i)+" "+firstNames.get(i)+"  StudentId : "+studentId.get(i));
            System.out.println("Assignment marks : "+assignment1.get(i)+" , "+assignment2.get(i)+" , "+assignment3.get(i)+
                "      Total Marks : "+totalmarks.get(i)+"\n");
        }
    }

    /**
     * Method threshold
     * this method allows the user to enter the threshold value 
     * and prints the total marks of the students which are less than the threshold
     */
    public void threshold(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the threshold value (0-100): ");
        int threshold=-1;
        try{
            threshold = sc.nextInt();
            while(threshold < 0 || threshold > 100 )
            {
                //This stateent will be displayed if the user has entered the value less than 0 or greater than 100
                System.out.println("Invalid input!!\nEnter a value in range (0 - 100) again :");
                threshold = sc.nextInt(); 
            }
            for(int i=0;i<totalmarks.size();i++)
                if(totalmarks.get(i)<threshold)
                    System.out.println("Name : "+lastNames.get(i)+" "+firstNames.get(i)+"  \nTotal Marks : "+totalmarks.get(i)+"\n");
        }
        catch(Exception e)
        {
            System.out.println("Invalid input!!!\nEnter an Integer value only");
            //This stateent will be displayed if the user has entered any string
            threshold();
        }    
    }

    /**
     * Method top10MaxMin
     * this method is to find the top 10 max and min marks of the students 
     * among all the students
     */
    public void top10MaxMin(String choice)
    { 
        Map<Double,String> Topten = new HashMap<Double,String>();
        for(int j=0;j<totalmarks.size();j++){
            String temp = lastNames.get(j) + " " +firstNames.get(j);      
            Topten.put(totalmarks.get(j),temp);}
        TreeMap<Double,String> sorted = new TreeMap<Double,String>(Topten);   
        Set<Map.Entry<Double,String>> entryset = sorted.entrySet();
        Map.Entry<Double,String>[] entryarray = entryset.toArray(new Map.Entry[entryset.size()]);
        switch(choice)
        {

            case "max":System.out.println("Top 10 Students with Highest marks : ");
                //This function will be displayed if user has choosen the option of printing the top 10 highest marks
                for(int i=sorted.size()-1,j=0;j<10;j++,i--)
                    System.out.println((j+1)+")  Name : "+entryarray[i].getValue()+"\n    Total Marks : "+entryarray[i].getKey()+"\n");
                break;
            case "min":System.out.println("Top 10 Students with lowest marks : ");
                //This function will be displayed if user has choosen the option of printing the top 10 lowest marks
                for(int i=0;i<10;i++)
                    System.out.println((i+1)+")  Name : "+entryarray[i].getValue()+"\n    Total Marks : "+entryarray[i].getKey()+"\n");
                break;         
        }
    }

    /**
     * Method displayMenu
     * this method is used to allow the user to enter an option from the given options and
     * print the desired function only and allows the user to end the execution. 
     */
    public void displayMenu(){
        do{
            System.out.println("***************MENU***************\n1. Display All Student Details\n2. Display Marks Based on Threshold");
            System.out.print("3. Display Top 10 Highest Marks\n4. Display Top 10 Lowest Marks\n5. Exit\nEnter an option : ");
            int choice = -1;
            try{
                choice = (new Scanner(System.in)).nextInt();}
            catch(Exception e){
                System.out.println("Enter an appropriate interger option value");
                displayMenu();}
            switch(choice){
                case 1 : this.display(); break;
                case 2 : this.threshold(); break;
                case 3 : this.top10MaxMin("max"); break;
                case 4 : this.top10MaxMin("min"); break;
                case 5 :System.out.println("Thank you :) "); 
                    System.exit(0);
                default : System.out.println("please enter an appropriate option value."); 
            }
        }while(true);
    }

    public static void main(String[] args){
        Student myObj=new Student();
        myObj.readFile();
        myObj.displayMenu();
    }
}


