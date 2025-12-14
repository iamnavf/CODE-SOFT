import java.util.Random;
import java.util.Scanner;
class random{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int loose=0;
        int count=0;
        int limit=5;
       while(limit!=0){
         System.out.println("enter the number between 1 to 100");
       int num = scan.nextInt();
        Random rn = new Random();
       int random_num=rn.nextInt(100);
       if(num==random_num){
        System.out.println(random_num+","+num);
        count++;
        System.out.println(count);
        System.out.println(loose);
       }
       else{
        System.out.println("incorrect :::"+"random number "+random_num+",   your guess   "+num);
        loose++;
        System.out.println("win:::::"+count);
        System.out.println("loose:::"+loose);
        if(random_num<num){
            System.out.println("random number is too low ::::" + random_num);
        }
        else{
            
            System.out.println("random number is  too high :::::" + random_num);
        }
         limit--;
       System.out.println("remaing attempt:  "+limit);
       }
       }
    }
}