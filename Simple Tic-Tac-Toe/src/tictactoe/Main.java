package tictactoe;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character[][] area = createArea();
        showArea(area);
        move(area, scanner);
    }

    public static Character[][] createArea() {
        Character[][] area = new Character[3][3];
        for (Character[] characters : area) {
            Arrays.fill(characters, ' ');
        }
        return area;
    }

    public static void showArea(Character[][] area) {
        System.out.println("---------");
        for (Character[] characters : area) {
            System.out.print("| ");
            for (Character character : characters) {
                System.out.print(character + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String checkArea(Character[][] area) {
        String result = "";
        int count = 0;
        while (count < area.length) {
            if (( area[count][0] == area[count][1] && area[count][1] == area[count][2]) &&
               (area[count][0] != ' ' && area[count][1] != ' ' && area[count][2] != ' ')) {
                result = area[count][0] == 'X' ? "X wins" : "O wins";
                break;
            } else if ((area[0][count] == area[1][count] && area[1][count] == area[2][count]) &&
                    (area[0][count] != ' ' && area[1][count] != ' ' && area[2][count] != ' ')){
                result = area[0][count] == 'X' ? "X wins" : "O wins";
                break;
            } else if ((area[1][1] != ' ' && area[0][0] == area[1][1] && area[1][1] == area[2][2]) ||
                    (area[1][1] != ' ' && area[0][2] == area[1][1] && area[1][1] == area[2][0])) {
                result = area[1][1] == 'X' ? "X wins" : "O wins";
                break;
            } else {
                result = "Draw";
            }
            count++;
        }
        return result;

    }

    public static void move(Character[][] area, Scanner scanner) {
        String result;
        int moveCount = 0;
        while (true) {
            int a;
            int b;
            try {
                System.out.print("Enter the coordinates: ");
                String[] cord = scanner.nextLine().split(" ");
                a = Integer.parseInt(cord[0]) - 1;
                b = Integer.parseInt(cord[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (a > 2 || b > 2 || a < 0 || b < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (area[a][b] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                if (moveCount % 2 == 0) {
                    area[a][b] = 'X';
                } else {
                    area[a][b] = 'O';
                }
                moveCount++;
            }
            showArea(area);

            result = checkArea(area);
            if (result.equals("X wins" ) || result.equals("O wins")) {
                System.out.println(result);
                break;
            } else if (moveCount == 9) {
                System.out.println(result);
                break;
            }
        }
    }
}
