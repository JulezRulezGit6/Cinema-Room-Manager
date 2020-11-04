import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] array = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        int demandedSpaces = scanner.nextInt();

        int consecutive = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (array[i][j] == 0) {
                    consecutive++;
                    if (consecutive == demandedSpaces) {
                        System.out.println(i + 1);

                        return;
                    }
                } else {
                    consecutive = 0;
                }
            }
            consecutive = 0;

        }
        System.out.println("0");
    }
}