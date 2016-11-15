package sample;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 */
public class Knight extends Pieces {
    @Override
    public List<Cell> check(Cell[][] board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if(getColor() == false) { //s�raya gore islem yap�yor false = siyah
            // 2 sa�a, 1 yukar�
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 2 sa�a, 1 asa��
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 2 sola, 1 yukar�
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 2 sola, 1 asa��
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 1 sa�a, 2 a�a��
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y+1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 1 sa�a, 2 yukar�
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y+1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 1 sola, 2 a�a��
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y-1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 1 sola, 2 yukar�
            if(y-1 >= 0 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y-1]))
                moves.add(new Cell(board[x+2][y-1]));
        }
        else if(getColor() == true) { //s�raya gore islem yap�yor true = beyaz
            // 2 sa�a, 1 yukar�
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 2 sa�a, 1 asa��
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 2 sola, 1 yukar�
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 2 sola, 1 asa��
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 1 sa�a, 2 a�a��
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y + 1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 1 sa�a, 2 yukar�
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfWhite(board[x + 2][y + 1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 1 sola, 2 a�a��
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y - 1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 1 sola, 2 yukar�
            if(y-1 >= 0 && x+2 < 8 && !Controller.isEnemyOfWhite(board[x+2][y-1]))
                moves.add(new Cell(board[x+2][y-1]));
        }
        return moves;
    }

    @Override
    public void setColor(boolean colors) {
        super.setColor(colors);
    }

    @Override
    public boolean getColor() {
        return super.getColor();
    }
}
