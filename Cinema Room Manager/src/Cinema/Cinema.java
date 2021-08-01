package cinema;
import java.util.Scanner;

public class Cinema {

    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        String[][] cinema = new String[rows + 1][seats + 1];

        createCinema(cinema);
        showMenu(cinema, rows, seats);
    }


    public static void createCinema(String[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
    }

    public static void showMenu(String[][] cinema, int rows, int seats) {
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int n = scanner.nextInt();

            if (n == 0) {
                break;
            } else if (n == 1) {
                showTheSeats(cinema);
                System.out.println();
            } else if (n == 2) {
                buyTicket(cinema, rows, seats);
                System.out.println();
            } else  if (n == 3) {
                showStatistic(cinema, rows, seats);
            }
        }
    }

    public static void showTheSeats(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (String[] strings : cinema) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(String[][] cinema, int rows, int seats) {
        while (true) {
            System.out.println();
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row > cinema.length - 1 || seat > cinema[1].length - 1 || row == 0 || seat == 0) {
                System.out.println("Wrong input!");
            } else {
                if (cinema[row][seat].equals("B")) {
                    System.out.println();
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinema[row][seat] = "B";
                    int ticketPrice;
                    int totalSeats = rows * seats;
                    if (totalSeats < 60) {
                        ticketPrice = 10;
                    } else {
                        int eightDollarsRows = rows / 2 + rows % 2;
                        if (row < eightDollarsRows) {
                            ticketPrice = 10;
                        } else {
                            ticketPrice = 8;
                        }
                    }
                    System.out.println("Ticket price: $" + ticketPrice);
                    break;
                }
            }
        }
    }

    public static void showStatistic(String[][] cinema ,int rows, int seats) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int eightDollarsRows = rows / 2 + rows % 2;
        int totalIncome;
        int totalSeats = rows * seats;
        int tenDollarRows;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j].equals("B")) {
                    purchasedTickets++;
                    if (i < eightDollarsRows) {
                        currentIncome += 10;
                    } else {
                        currentIncome += 8;
                    }
                }
            }
        }

        if (totalSeats < 60) {
            totalIncome = totalSeats * 10;
        } else {
            tenDollarRows = rows - eightDollarsRows;
            totalIncome = (tenDollarRows * seats * 10 + eightDollarsRows * 8 * seats);
        }

        double percentage = (double) purchasedTickets / totalSeats * 100 ;
        System.out.println();
        System.out.printf("Number of purchased tickets: %d", purchasedTickets);
        System.out.println();
        System.out.printf("Percentage: %.2f%%", percentage);
        System.out.println();
        System.out.printf("Current income: $%d", currentIncome);
        System.out.println();
        System.out.printf("Total income: $%d", totalIncome);
        System.out.println();
        System.out.println();
    }
}