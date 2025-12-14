import java.util.Scanner;
public class grade {
    public static void main(String[] args) {
        System.out.println("STUDENT GRADE CALCULATOR");
        Scanner scan= new Scanner(System.in);
        int arr[]=new int[5];
        for(int i=0;i<5;i++){
            System.out.println("enter the mark in subject "+(i+1)+" :");
            arr[i]=scan.nextInt();
        }
        int total=0;
        for(int j=0;j<5;j++){
            total=total+arr[j];
            
        }
        int avg=total/5;
        System.out.println(" YOUR TOTAL "+total);
         System.out.println(" YOUR AVERAGE FOR 5 SUBJECT "+avg);
         int grade=(avg/10);
        switch (grade) {
            case 10 :
                System.out.println(" YOUR GRADE  O");
                break;
            case 9 :
                System.out.println(" YOUR GRADE  A+");
                break;
            case 8:
                System.out.println(" YOUR GRADE  A");
                break;
            case 7 :
                System.out.println(" YOUR GRADE  B+");
                break;
            case 6 :
                System.out.println(" YOUR GRADE  B");
                break;
            case 5 :
                System.out.println(" YOUR GRADE  C");
                break;
            default:
                System.out.println(" YOUR GRADE  FAIL");
                break;
        }
    }
}
