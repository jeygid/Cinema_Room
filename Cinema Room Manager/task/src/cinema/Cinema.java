package cinema;

import java.util.Scanner;

public class Cinema {

    public static final Scanner scanner = new Scanner(System.in);

    public static char[][] cinema;
    public static int rows;
    public static int seats;

    public static int numberOfPurchasedTickets;
    public static int currentIncome;
    public static int totalSeats;
    public static int totalIncome;

    public static void printCinema() {

        System.out.println("\nCinema:");
        System.out.print("  ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buySeat() {

        int rowNumber;
        int seatNumber;

        while (true) {

            System.out.println("\nEnter a row number:");
            rowNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter a seat number in that row:");
            seatNumber = Integer.parseInt(scanner.nextLine());

            if ((rowNumber - 1) >= rows || (rowNumber - 1) < 0 ||
                    (seatNumber - 1) >= seats || (seatNumber - 1) < 0
            ) {
                System.out.println("\nWrong input!");

            } else {

                if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {

                    System.out.println("\nThat ticket has already been purchased!");

                } else {
                    cinema[rowNumber - 1][seatNumber - 1] = 'B';

                    int price = 0;

                    if (seats * rows < 60) {
                        price = 10;
                    } else {
                        if (rowNumber <= rows / 2) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }

                    numberOfPurchasedTickets++;
                    currentIncome += price;

                    System.out.println("\nTicket price: $" + price);

                    break;
                }

            }
        }

    }


    public static void getStats() {
        double percentage = 100 * (double) numberOfPurchasedTickets / totalSeats;
        String percent = String.format("%.2f",percentage);
        System.out.println("\nNumber of purchased tickets: " + numberOfPurchasedTickets + "\n" +
                "Percentage: " + percent + "%\n" +
                "Current income: $" + currentIncome + "\n" +
                "Total income: $" + totalIncome);
    }

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        rows = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        seats = Integer.parseInt(scanner.nextLine());

        cinema = new char[rows][seats];

        if (seats * rows < 60) {
            totalIncome = 10 * seats * rows;
        } else {
            if (rows % 2 == 0) {
                totalIncome = (rows * seats / 2) * 8 + (rows * seats / 2) * 10;
            } else {
                totalIncome = seats * (1 + rows / 2) * 8 + seats * (rows / 2) * 10;
            }
        }

        totalSeats = seats * rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }

        String command = "";

        while (!command.equals("0")) {

            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    printCinema();
                    break;
                case "2":
                    buySeat();
                    break;
                case "3":
                    getStats();
                    break;
                default:
                    break;
            }
        }
    }
}