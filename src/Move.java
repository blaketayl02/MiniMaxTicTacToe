public class Move {
    int row, col;
    static char oh = 'o', ex = 'x';

    static boolean movesLeft(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;
    }


    static int minimax(char board[][], int depth, boolean isMax) {
        int score = evaluate(board);
        int bestVal = 0;

        if (!movesLeft(board)) {
            return score;
        }

        if (score == 10) {
            return score;
        } else if (score == -10) {
            return score;
        }

        if (isMax) {
            bestVal = -1000;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = ex;
                        bestVal = Math.max(minimax(board, depth + 1, false), bestVal);
                        board[i][j] = '_';
                    }
                }
            }

            return bestVal;
        } else {
            bestVal = 1000;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = oh;
                        bestVal = Math.min(minimax(board, depth + 1, true), bestVal);
                        board[i][j] = '_';
                    }
                }
            }
            return bestVal;
        }
    }


    static int evaluate(char board[][]) {
        //check rows for win
       for (int row = 0; row < board.length; row++) {
           if (board[row][0] == oh && board[row][1] == oh && board[row][2] == oh) {
               return -10;
           } else if (board[row][0] == ex && board[row][1] == ex && board[row][2] == ex) {
               return +10;
           }
       }

        //check col for win
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == oh && board[1][col] == oh && board[2][col] == oh) {
                return -10;
            } else if (board[0][col] == ex && board[1][col] == ex && board[2][col] == ex) {
                return +10;
            }
        }

        //check diag for win
        if (board[0][0] == ex && board[1][1] == ex && board[2][2] == ex) {
            return +10;
        } else if (board[0][0] == oh && board[1][1] == oh && board[2][2] == oh) {
            return -10;
        }
        if (board[0][2] == ex && board[1][1] == ex && board[2][0] == ex) {
            return +10;
        } else if (board[0][2] == oh && board[1][1] == oh && board[2][0] == oh) {
            return -10;
        }

        //draw
        return 0;
    }


    static Move findBestMove(char board[][]) {
        Move bestMove = new Move();
        int bestVal = -1000;


        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == '_') {
                    board[row][col] = ex;
                    int moveVal = minimax(board, 0, false);
                    board[row][col] = '_';

                    if (moveVal > bestVal) {
                        bestMove.row = row;
                        bestMove.col = col;
                        bestVal = moveVal;
                    }
                }

            }
        }
        return bestMove;
    }


    static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " : ");
            }
            System.out.println();
            System.out.println("...........");
        }
        System.out.println();
    }
}
