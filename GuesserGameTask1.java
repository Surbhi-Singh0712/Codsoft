package ProjectCodsoft;

import java.util.Random;
import java.util.Scanner;
public class GuesserGameTask1 {
    private static final int MIN_RANGE=1;
    private static final int  MAX_RANGE=100;
    private static final int MAX_ATTEMPS=7;
    private static final int MAX_ROUND=3;

    public static void main(String[] args){
        Random random=new Random();
        Scanner sc=new Scanner(System.in);
        int score=0;
        int totalAttempts = 0;
        int attempts=0;
        System.out.println("NUMBER GUESSING GAME");
        System.out.println("Total Number Of Round:"+MAX_ROUND);
        System.out.println("Number Of Attempts In Each Round:"+MAX_ATTEMPS);

        for(int i=1;i<=MAX_ROUND;i++){
            int generatedNumber=random.nextInt(MAX_RANGE)+MIN_RANGE;
            System.out.println("Guess a number between "+ MIN_RANGE +" to "+ MAX_RANGE+" :");

            while(attempts<MAX_ATTEMPS){
                System.out.println("Enter Your GUESS: ");
                int userGuess=sc.nextInt();
                attempts++;
                totalAttempts++;

                if(userGuess==generatedNumber){
                    System.out.println("Congratulations! You guessed the correct number.");
                    score++;
                    break;
                }
                else if(userGuess>generatedNumber){
                    System.out.println("Generated Number is less than "+userGuess+" ,Try again!");
                }
                else
                {
                    System.out.println("Generated Number is greater than "+userGuess+" ,Try again!");
                }
                if(attempts==MAX_ATTEMPS){
                    System.out.println("Sorry, you didn't guess the correct number. The correct value was " + generatedNumber + ".");
                }

            }


            System.out.println("Your Score is "+score+" out of "+MAX_ROUND+"rounds.");
            System.out.println("Total Attempts: " + totalAttempts);
            System.out.println("play again!(y/n)");
            char getUserInputChar=sc.next().charAt(0);
            if(getUserInputChar=='n'||getUserInputChar=='N'){
                break;
            }
            else {
                System.out.println("YOU CAN PLAY ");
            }
            attempts = 0;
        }
        if (score > 0) {
            System.out.println("Thank you for playing! Your final score is " + score + " out of " + MAX_ROUND + " rounds.");
        } else {
            System.out.println("Thank you for playing! Better luck next time.");
        }

    }

}
