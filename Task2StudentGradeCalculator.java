package ProjectCodsoft;


import java.util.*;

public class Task2StudentGradeCalculator {
    private static int calTotalMark(float marks[]){
        int totalMark=0;
        for(float mark:marks){
            totalMark+=mark;
        }
        return totalMark;
    }
    private static float AvgPercent(float[] marks,int TotalMaxMarks){

        int n=marks.length;
        float totalMark = calTotalMark(marks);
        float averagePercent=((totalMark/TotalMaxMarks)*100);


        return averagePercent;
    }
    private static String gradeCalculation(float[] marks,int TotalMaxMarks){
        float val= AvgPercent(marks,TotalMaxMarks);

        if(val<=100 &&val>=95) return "A1";
        else if(val<=94 &&val>=90) return "A2";
        else if(val<=89 &&val>=85) return "B1";
        else if(val<=84 &&val>=80) return "B2";
        else if(val<=79 &&val>=75) return "C1";
        else if(val<=74 &&val>=70) return "C2";
        else if(val<=69 &&val>=60) return "D1";
        else if(val<=59 &&val>=56) return "D2";
        else if (val<=55&&val>=33) return "E";
        else return "Fail";
    }
    private static void DisplayResult(float[] marks,int TotalMaxMarks){
        System.out.println("      <----STUDENT GRADE CALCULATION---->      ");
        System.out.println("SUM OF TOTAL MARKS OF ALL SUBJECT IS: "+calTotalMark(marks));
        System.out.println( "AVERAGE PERCENTAGE is: "+AvgPercent(marks,TotalMaxMarks)+"%");
        System.out.println("STUDENT POSITIONAL GRADE: "+gradeCalculation(marks,TotalMaxMarks));
        System.out.println("      <      ---------------------------      >      ");
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of Subject: ");
        int n=sc.nextInt();
        System.out.println("Enter the MAXIMUM MARKS of each subject: ");
        int max=sc.nextInt();
        System.out.println("Enter Marks of all subject: ");
        int TotalMaxMarks=max*n;
        float marks[]=new float[n];
        for(int i=0;i<n;i++){
            marks[i]=sc.nextInt();
        }

        DisplayResult(marks,TotalMaxMarks);

    }
}
