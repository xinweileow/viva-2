import java.util.Scanner;
import java.util.Random;

public class LuckyDraw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Read number length
        System.out.print("Enter number length (3 or 4): ");
        int length =  sc.nextInt();
        // Read number of ball to choose
        System.out.print("Enter number of ball to choose: ");
        int numOfBall = sc.nextInt();
        // Read ball value
        System.out.print("Enter ball value: ");
        int ballValue = sc.nextInt();
        // Read target number
        System.out.print("Target number: ");
        int target = sc.nextInt();

        // Generate random number using generateNum method
        int[] num = generateNum(numOfBall, length);

        // Declare variable for future use
        int almostNum = 0;
        boolean found = false;
        boolean almostFound = false;

        // Display number grid using displayNum method
        displayNum(num ,numOfBall);
        System.out.println();

        // Find the target and the near miss number from the number grid
        for (int poolNumber : num){
            if (foundTarget(target, poolNumber)){
                found = true;
                break;
            }else if (nearMiss(target, poolNumber, length)){
                almostFound = true;
                almostNum = poolNumber;
            }
        }

        // Display message
        if (found) {
            System.out.println("Congratulations! You Got The Number " + target);
            double prize = (double) (length * ballValue * 1000) / numOfBall;
            System.out.printf("Total Prize: RM%.2f", prize );
        } else if (almostFound) {
            System.out.println("You almost get it");
            System.out.print(almostNum + "\t");
        } else {
            System.out.println("Try again next time");
        }
    }

    /* Generate random number method */
    static int[] generateNum(int numOfBall, int length){
        Random rand = new Random();
        int[] num = new int[numOfBall];
        if (length == 3) {
            for (int i = 0; i < numOfBall; i++) {
                num[i] = rand.nextInt(1000) + 1;
            }
        }else if (length == 4) {
            for (int i = 0; i < numOfBall; i++) {
                num[i] = rand.nextInt(10000) + 1;
            }
        }
        return num;
    }

    /* Display number grid method */
    static void displayNum(int[] num, int numOfBall){
        // Get number of ball per line
        int ballPerLine;
        ballPerLine = (int)Math.sqrt(numOfBall);
        int count = 0;
        // Display the number in a grid layout
        for (int i = 0; i < num.length; i++) {
            System.out.printf("%4d\t", num[i]);
            count ++;
            if (count == ballPerLine) {
                System.out.println();
                count = 0;
            }
        }
    }

    /* Found the target method */
    static boolean foundTarget(int target, int poolNumber){
        return (target == poolNumber);
    }

    /* Found the near miss number method */
    static boolean nearMiss(int target, int poolNumber, int length){
        int sameCount = 0;
        int tempTarget = target;
        int tempPool = poolNumber;
        for (int i = 0; i < length; i++) {
            int digit1 = tempTarget % 10;  // Get the last digit of the target
            int digit2 = tempPool % 10;    // Get the last digit of the pool number
            if (digit1 == digit2){
                sameCount++;
            }
            tempTarget /= 10;              // Remove the last digit of the target
            tempPool /= 10;                // Remove the last digit of the pool number
        }
        return (sameCount == length - 1);  // Return the near miss number
    }
}

