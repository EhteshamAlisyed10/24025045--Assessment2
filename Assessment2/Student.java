/**
 * Assessment2 
 *
 * @author (Ehtesham Ali Syed - 24025045)
 * @version (8-04-2022)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
public class Student
{
    // instance variables - replace the example below with your own
    private ArrayList<String> lastNames;
    private ArrayList<String> firstNames;
    private ArrayList<Integer> studentId;
    private ArrayList<Double> assignment1;
    private ArrayList<Double> assignment2;
    private ArrayList<Double> assignment3;
    private ArrayList<Double> totalmarks;
    private String line;
    private String firstLine;
    private String[] tokens;
    public Student()
    {
        // initialise instance variables
        lastNames = new ArrayList<String>();
        firstNames = new ArrayList<String>();
        studentId = new ArrayList<Integer>();
        assignment1 = new ArrayList<Double>();
        assignment2 = new ArrayList<Double>();
        assignment3 = new ArrayList<Double>();
        totalmarks = new ArrayList<Double>();
    }

    public void readFile(){
        int countLines=0;
        try{
            File myFile=new File("student.csv");
            Scanner myScanner=new Scanner(myFile);
            firstLine = myScanner.nextLine();
            myScanner.nextLine();
            while(myScanner.hasNextLine()){
                line=myScanner.nextLine();
                countLines++;
                //System.out.println(line);
                if(countLines>1){
                    tokens = line.split(",");
                    setlastNames();
                    setfirstNames();
                    setstudentId();
                    setassignment1();
                    setassignment2();
                    setassignment3();
                }
            }
            double marks;
            for(int i=0 ; i < assignment1.size(); i++){
                marks = assignment1.get(i) + assignment2.get(i) + assignment3.get(i);
                totalmarks.add(marks);
                }
            myScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
    }

    public void setlastNames(){
        lastNames.add(tokens[0]);
    }
    public void setfirstNames(){
        firstNames.add(tokens[1]);
    }
    public void setstudentId(){
        studentId.add(Integer.valueOf(tokens[2]));
    }
    public void setassignment1(){
        if(tokens.length<4){
            assignment1.add(0.0);
            return;}
        if(tokens[3].equals(""))
            tokens[3] = "0.0";
        assignment1.add(Double.valueOf(tokens[3]));
    }
    public void setassignment2(){
        if(tokens.length<5){
            assignment2.add(0.0);
            return;}
        if(tokens[4].equals(""))
            tokens[4] = "0.0";
        assignment2.add(Double.valueOf(tokens[4]));
    }
    public void setassignment3(){
        if(tokens.length<6){
            assignment3.add(0.0);
            return;}
        if(tokens[5].equals(""))
            tokens[5] = "0.0";
        assignment3.add(Double.valueOf(tokens[5]));
    }
    public void display(){
        System.out.println(firstLine);
        for(int i = 0 ; i < lastNames.size() ; i++){
            System.out.println("Name : "+lastNames.get(i)+" "+firstNames.get(i)+"  StudentId : "+studentId.get(i));
            System.out.println("Assignment marks : "+assignment1.get(i)+" , "+assignment2.get(i)+" , "+assignment3.get(i)+
            " \nTotal Marks : "+totalmarks.get(i)+"\n");
        }
    }
       public void threshold(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the threshold value (0-100): ");
        int threshold=-1;
        try{
            threshold = sc.nextInt();
            while(threshold < 0 || threshold > 100 ){
                System.out.println("Invalid input!!\nEnter a value in range (0 - 100) again :");
                threshold = sc.nextInt(); }
            for(int i=0;i<totalmarks.size();i++)
                if(totalmarks.get(i)<threshold)
                    System.out.println("Name : "+lastNames.get(i)+" "+firstNames.get(i)+"  \nTotal Marks : "+totalmarks.get(i)+"\n");
        }
        catch(Exception e){
        System.out.println("Invalid input!!!\nEnter an Integer value only");
        threshold();
        }    
    }
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

    