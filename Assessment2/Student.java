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
     public static void main(String[] args){
        Student myObj=new Student();
        myObj.readFile();
        myObj.display();
    }
}

    