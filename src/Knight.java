import java.util.LinkedList;
import java.util.List;

/**
 * Created and implemented by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class Knight extends Pieces {
    @Override
    public List<Cell> checkMove(Cell[][] board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if(getColor() == false) { //siraya gore islem yapiyor false = siyah
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && (board[x+1][y+2].getPiece().getColor() == false || board[x+1][y+2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+1][y+2]));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && (board[x-1][y+2].getPiece().getColor() == false || board[x-1][y+2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-1][y+2]));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && (board[x+1][y-2].getPiece().getColor() == false|| board[x+1][y-2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+1][y-2]));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && (board[x-1][y-2].getPiece().getColor() == false || board[x-1][y-2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-1][y-2]));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && (board[x-2][y+1].getPiece().getColor() == false || board[x-2][y+1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-2][y+1]));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && (board[x+2][y+1].getPiece().getColor() == false || board[x+2][y+1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+2][y+1]));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && (board[x-2][y-1].getPiece().getColor() == false || board[x-2][y-1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-2][y-1]));
            // 2 sola, 1 yukari
            if(y-1 >= 0 && x+2 < 8 && (board[x+2][y-1].getPiece().getColor() == false || board[x+2][y-1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+2][y-1]));
        }
        else if(getColor() == true) { //siraya gore islem yapiyor true = beyaz
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && (board[x+1][y+2].getPiece().getColor() == true || board[x+1][y+2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+1][y+2]));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && (board[x-1][y+2].getPiece().getColor() == true || board[x-1][y+2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-1][y+2]));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && (board[x+1][y-2].getPiece().getColor() == true || board[x+1][y-2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+1][y-2]));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && (board[x-1][y-2].getPiece().getColor() == true || board[x-1][y-2].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-1][y-2]));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && (board[x-2][y+1].getPiece().getColor() == true || board[x-2][y+1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-2][y+1]));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && (board[x+2][y+1].getPiece().getColor() == true || board[x+2][y+1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x+2][y+1]));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && (board[x-2][y-1].getPiece().getColor() == true || board[x-2][y-1].getPiece() instanceof NoPiece))
                moves.add(new Cell(board[x-2][y-1]));
            // 2 sola, 1 yukari
            if(y-1 >= 0 && x+2 < 8 && (board[x+2][y-1].getPiece().getColor() == true || board[x+2][y-1].getPiece() instanceof NoPiece))
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
