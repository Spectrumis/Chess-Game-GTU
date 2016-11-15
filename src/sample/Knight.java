package sample;

import java.util.LinkedList;
import java.util.List;

/**
 * Created and implemented by GozdeDogan on 14.11.2016.
 */
public class Knight extends Pieces {
    @Override
    public List<Cell> checkMove(Cell[][] board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if(getColor() == false) { //siraya gore islem yapiyor false = siyah
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y+1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y+1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y-1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 2 sola, 1 yukari
            if(y-1 >= 0 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y-1]))
                moves.add(new Cell(board[x+2][y-1]));
        }
        else if(getColor() == true) { //s�raya gore islem yap�yor true = beyaz
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y + 1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfWhite(board[x + 2][y + 1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y - 1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 2 sola, 1 yukari
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
