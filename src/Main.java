import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        char board[][] = {{'_', '_', '_'},
                          {'_', '_', '_'},
                          {'_', '_', '_'}};

        // goal to make X win
        while (Move.movesLeft(board)) {
            System.out.println("=============================");
            Move.printBoard(board);
            if (Move.evaluate(board) == 10) {
                System.out.println("Sorry Computer Won!");
                break;
            } else if (Move.evaluate(board) == -10) {
                System.out.println("What? That's Impossible... You Won!");
                break;
            }
            System.out.println("Where would you like to play? Row: Col:");
            Scanner scanner = new Scanner(System.in);
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (board[row - 1][col - 1] != '_') {
                    System.out.println("Invalid Move. Cell is already occupied or out of bounds. Try again.");
                    continue;
                }
                board[row - 1][col - 1] = 'o';
                Move bestMove = Move.findBestMove(board);
                board[bestMove.row][bestMove.col] = 'x';
                System.out.println("bestMove is : " + (bestMove.row + 1) + " " + (bestMove.col + 1));
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.");
            }
        }
        if (Move.evaluate(board) == 0) {
            System.out.println("Looks like a draw...");
        }

    }
}