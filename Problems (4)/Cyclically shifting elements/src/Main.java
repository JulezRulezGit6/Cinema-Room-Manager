import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];


        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            int index = i - 1;
            if (index < 0) {
                index = length - 1;
            }
            result[i] = array[index];
        }


        for (int i = 0; i < length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}