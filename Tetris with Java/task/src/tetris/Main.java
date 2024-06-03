package tetris;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int col = sc.nextInt();
        int row = sc.nextInt();
        String test = sc.nextLine();

        Board board = new Board(row, col);
        board.print();
        System.out.println();
        board.addNewPiece(input);

        boolean exit = false;
        do{
            board.print();
            System.out.println();
            input = sc.nextLine();
            switch (input) {
                case "down":
                    board.movePieceDown();
                    break;
                case "right":
                    board.movePieceRight();
                    break;
                case "left":
                    board.movePieceLeft();
                    break;
                case "rotate":
                    board.rotatePiece();
                    break;
                case "exit":
                    exit = true;
                    break;
            }

        }while(!exit);

        sc.close();
    }
}

