import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NAndC {


    private static ArrayList<Integer> player1SquarePositions = new ArrayList<>();
    private static ArrayList<Integer> player2SquarePositions = new ArrayList<>();
    private char[][] board;

    /**
     * Constructor for game board
     * @param board the representation of the game board
     */
    public NAndC(char[][] board) {
        this.board = board;
    }

    public static void main(String[] args) {

        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};


        printBoard(board);
        while (true) {
            Scanner in = new Scanner(System.in);

            if (placeMark(board, in, player1SquarePositions, player2SquarePositions, "player1")) break;
            System.out.println();

            if (placeMark(board, in, player2SquarePositions, player1SquarePositions, "player2")) break;
        }

    }

    /**
     * places a mark on the board
     * @param board game board representation
     * @param in scanner for the position on the board
     * @param player1SquarePositions list of positions on the board where player1 placed their mark
     * @param player2SquarePositions list of positions on the board where player2 placed their mark
     * @param player username of the player
     * @return whether the mark has been placed
     */
    private static boolean placeMark(char[][] board, Scanner in, ArrayList<Integer> player1SquarePositions, ArrayList<Integer> player2SquarePositions, String player) {
        System.out.println("Enter the square number (1-9):");
        int squareNumX = in.nextInt();
        while(player1SquarePositions.contains(squareNumX) || player2SquarePositions.contains(squareNumX)){
            System.out.println("Select another square");
            squareNumX = in.nextInt();
        }
        makeMove(board, squareNumX, player);
        System.out.println(checkWinner());
        return checkWinner().length() > 0;
    }

    /**
     * manages players taking turn in the game
     * @param board representation of the game board
     * @param squareNum number identifying the position of each square
     * @param userName the name identifying the users
     */
    private static void makeMove(char[][] board, int squareNum, String userName) {
        char symbol = ' ';
        if (userName.equals("player1")) {
            symbol = 'X';
            player1SquarePositions.add(squareNum);
        } else if (userName.equals("player2")) {
            symbol = 'O';
            player2SquarePositions.add(squareNum);
        }

        switch (squareNum) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;

            default:
                break;

        }
        printBoard(board);

    }

    /**
     * prints out the outcome of the game
     * @return outcome of the game
     */
    private static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List lRow = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rCol = Arrays.asList(3, 6, 9);
        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(lRow);
        winning.add(midCol);
        winning.add(rCol);
        winning.add(diagonal1);
        winning.add(diagonal2);

        for(List l: winning){
            if(player1SquarePositions.containsAll(l)){
                return "player1 wins (X)";
            }
            else if(player2SquarePositions.containsAll(l)){
                return "player2 wins (O)";
            }
            else if((player1SquarePositions.size() + player2SquarePositions.size() == 9)){
                for(List t: winning) {
                    if (player1SquarePositions.containsAll(t)) {
                        return "player1 wins (X)";
                    } else if (player2SquarePositions.containsAll(t)) {
                        return "player2 wins (O)";
                    }
                }
                return "End of game, draw";
            }
        }
        return "";
    }

    /**
     * prints out the representation of the game board
     * @param board representation of the game board
     */
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

}