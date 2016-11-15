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

        if(getColor() == false) { //sýraya gore islem yapýyor false = siyah
            // 2 saða, 1 yukarý
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 2 saða, 1 asaðý
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 2 sola, 1 yukarý
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfBlack(board[x+1][y+2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 2 sola, 1 asaðý
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfBlack(board[x-1][y+2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 1 saða, 2 aþaðý
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y+1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 1 saða, 2 yukarý
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y+1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 1 sola, 2 aþaðý
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfBlack(board[x-2][y-1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 1 sola, 2 yukarý
            if(y-1 >= 0 && x+2 < 8 && !Controller.isEnemyOfBlack(board[x+2][y-1]))
                moves.add(new Cell(board[x+2][y-1]));
        }
        else if(getColor() == true) { //sýraya gore islem yapýyor true = beyaz
            // 2 saða, 1 yukarý
            if (y+2 < 8 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y+2]));
            // 2 saða, 1 asaðý
            if(y+2 < 8 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y+2]));
            // 2 sola, 1 yukarý
            if (y-2 >= 0 && x+1 < 8 && !Controller.isEnemyOfWhite(board[x + 1][y + 2]))
                moves.add(new Cell(board[x+1][y-2]));
            // 2 sola, 1 asaðý
            if(y-2 >= 0 && x-1 >= 0 && !Controller.isEnemyOfWhite(board[x - 1][y + 2]))
                moves.add(new Cell(board[x-1][y-2]));
            // 1 saða, 2 aþaðý
            if(y+1 < 8 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y + 1]))
                moves.add(new Cell(board[x-2][y+1]));
            // 1 saða, 2 yukarý
            if(y+1 < 8 && x+2 < 8 && !Controller.isEnemyOfWhite(board[x + 2][y + 1]))
                moves.add(new Cell(board[x+2][y+1]));
            // 1 sola, 2 aþaðý
            if(y-1 >= 0 && x-2 >= 0 && !Controller.isEnemyOfWhite(board[x - 2][y - 1]))
                moves.add(new Cell(board[x-2][y-1]));
            // 1 sola, 2 yukarý
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
