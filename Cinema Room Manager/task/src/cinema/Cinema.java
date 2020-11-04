package cinema;

import java.util.Scanner;

public class Cinema {

    private static final int EXPENSIVE_TICKET_PRICE = 10;
    private static final int INEXPENSIVE_TICKET_PRICE = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int columns = scanner.nextInt();

        char[][] scheme = fillScheme(rows, columns);


        while (true) {
            System.out.print("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit\n" +
                    ">");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    printScheme(scheme);
                    break;
                case 2:
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("Enter a row number:");
                        System.out.print("> ");
                        int rowNumber = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        System.out.print("> ");
                        int columnNumber = scanner.nextInt();

                        int price = getTicketPrice(rows, columns, rowNumber);
                        if (rowNumber > rows || columnNumber > columns || rowNumber < 1 || columnNumber < 0) {
                            System.out.println("Wrong input!\n");
                        } else if (scheme[rowNumber][columnNumber] == 'B') {
                            System.out.println("That ticket has already been purchased\n");
                        } else {
                            validInput = true;
                            scheme[rowNumber][columnNumber] = 'B';
                            System.out.print("Ticket price: $");
                            System.out.println(price + "\n");

                        }
                    }
                    break;
                case 3:
                    int numberOfPurchasedTickets = getNumberOfPurchasedTickets(scheme, 1, scheme.length);
                    System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
                    double capacity = rows * columns;
                    double occupancy = numberOfPurchasedTickets / capacity * 100;
                    System.out.println("Percentage: " + String.format("%.2f", occupancy) + "%");
                    System.out.println("Current income: $" + calculateRevenue(scheme));
                    System.out.println("Total income: $" + calculateTargetRevenue(scheme));
                    break;
                case 0:
                    return;
            }

        }
    }


    private static int getTicketPrice(int rows, int columns, int rowNumber) {
        int price;
        int middle = rows / 2;

        if (rows * columns < 60) {
            price = EXPENSIVE_TICKET_PRICE;
        } else {
            if (rowNumber <= middle) {
                price = EXPENSIVE_TICKET_PRICE;
            } else {
                price = INEXPENSIVE_TICKET_PRICE;
            }
        }
        return price;
    }

    private static int getNumberOfPurchasedTickets(char[][] scheme, int startRow, int endRow) {
        int purchasedTickets = 0;
        for (int i = startRow; i < endRow; i++) {
            for (int j = 1; j < scheme[0].length; j++) {
                if (scheme[i][j] == 'B') {
                    purchasedTickets++;
                }
            }
        }
        return purchasedTickets;
    }

    private static void printScheme(char[][] scheme) {
        System.out.println("Cinema: ");
        for (int i = 0; i < scheme.length; i++) {
            for (int j = 0; j < scheme[0].length; j++) {
                System.out.print(scheme[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] fillScheme(int rows, int columns) {
        rows++;
        columns++;
        char[][] array = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                    array[i][j] = ' ';
                } else if (i == 0) {
                    array[i][j] = (char) (j + '0');
                } else if (j == 0) {
                    array[i][j] = (char) (i + '0');
                } else {
                    array[i][j] = 'S';
                }
            }
        }
        return array;
    }

    public static int calculateRevenue(char[][] scheme) {
        int rows = scheme.length - 1;
        int columns = scheme[0].length - 1;

        if (rows * columns <= 60) {
            int numberOfPurchasedTickets = getNumberOfPurchasedTickets(scheme, 1, scheme.length);

            return numberOfPurchasedTickets * EXPENSIVE_TICKET_PRICE;
        } else {

            int lastExpensiveRow = rows / 2;

            int numberOfExpensiveTickets = getNumberOfPurchasedTickets(scheme, 1, lastExpensiveRow + 1);
            int numberOfInexpensiveTickets = getNumberOfPurchasedTickets(scheme, lastExpensiveRow + 1, scheme.length);
            return numberOfExpensiveTickets * EXPENSIVE_TICKET_PRICE + numberOfInexpensiveTickets * INEXPENSIVE_TICKET_PRICE;
        }
    }

    public static int calculateTargetRevenue(char[][] scheme) {
        int rows = scheme.length - 1;
        int columns = scheme[0].length - 1;
        int expensiveRows = rows / 2;
        int inexpensiveRows = rows - expensiveRows;
        if (rows * columns <= 60) {
            return rows * columns * EXPENSIVE_TICKET_PRICE;
        } else {
            return (expensiveRows * EXPENSIVE_TICKET_PRICE + inexpensiveRows * INEXPENSIVE_TICKET_PRICE) * columns;
        }

    }


}