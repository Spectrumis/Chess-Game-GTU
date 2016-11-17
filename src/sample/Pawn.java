package sample;

import java.util.LinkedList;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * Created by Selman Ahatlı on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class Pawn extends Pieces {
    public Pawn(){

    }

    @Override
    public List<Cell> checkMove(Cell[][] board, int x, int y) {
        List<Cell> moves = new LinkedList<>();
        if (getColor() == false) { //sıraya gore islem yapıyor false = siyah
            //markButton(board[X][Y]);
            System.out.printf("X: %d, Y: %d\n", x, y);
            if (y > 0 && !Controller.isEnemyOfBlack(board[x][y - 1])
                    && y - 1 >= 0 && y - 1 < 8) {
                moves.add(new Cell(board[x][y - 1]));
                //Main.markMove(board[X][Y - 1]);
            }
            if (x > 0 && y > 0 && Controller.isEnemyOfBlack(board[x - 1][y - 1])) {
                moves.add(new Cell(board[x - 1][y - 1]));
                //Main.markMove(board[X - 1][Y - 1]);
            }
            if (x < 7 && y > 0 && Controller.isEnemyOfBlack(board[x + 1][y - 1])) {
                moves.add(new Cell(board[x + 1][y - 1]));
                //Main.markMove(board[X + 1][Y - 1]);
            }
            if (y < 7 && y > 0 && !Controller.isEnemyOfBlack(board[x][y - 1])
                    && y - 2 >= 0 && y - 2 < 8 && y == 6) {
                moves.add(new Cell(board[x][y - 1]));
                //Main.markMove(board[X][Y - 2]);
            }
        } else if (getColor() == true) { //true =beyaz
            //markButton(board[X][Y]);
            System.out.printf("X: %d, Y: %d\n", x, y);
            if (y > 0 && !Controller.isEnemyOfWhite(board[x][y + 1])
                    && y + 1 >= 0 && y + 1 < 8) {
                moves.add(new Cell(board[x][y + 1]));
                //Main.markMove(board[X][Y - 1]);
            }
            if (x > 0 && y > 0 && Controller.isEnemyOfWhite(board[x - 1][y + 1])) {
                moves.add(new Cell(board[x - 1][y + 1]));
                //Main.markMove(board[X - 1][Y - 1]);
            }
            if (x < 7 && y > 0 && Controller.isEnemyOfWhite(board[x + 1][y + 1])) {
                moves.add(new Cell(board[x + 1][y + 1]));
                //Main.markMove(board[X + 1][Y - 1]);
            }
            if (y < 7 && y > 0 && !Controller.isEnemyOfWhite(board[x][y + 1])
                    && y - 2 >= 0 && y - 2 < 8 && y == 2) {
                moves.add(new Cell(board[x][y + 1]));
                //Main.markMove(board[X][Y - 2]);
            }

        }
        return moves;
    }
    public void setColor(boolean colors){
        super.setColor(colors);
    }
    public boolean getColor(){
        return super.getColor();
    }
}
