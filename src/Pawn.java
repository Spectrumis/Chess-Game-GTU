import java.util.LinkedList;
import java.util.List;

/**
 * Created by Selman Ahatlı on 14.11.2016.
 * Implemented by Selman Ahatlı on 14.11.2016.
 */
public class Pawn extends Pieces {

    public boolean firstMove = true;

    @Override
    public List<Cell> checkMove(Cell[][] board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if (getColor() == true)
        {
            if (firstMove == true)
            {
                if (board[x+2][y].getPiece() instanceof NoPiece)
                {
                    moves.add(new Cell(board[x+2][y]));
                }
            }

            //yukarı
            if (x+1 < 8 && board[x+1][y].getPiece() instanceof NoPiece)
            {
                moves.add(new Cell(board[x+1][y]));
            }

            //sag capraz
            if (x+1 < 8 && y+1 < 8 && board[x+1][y+1].getPiece().getColor() == false)
            {
                if (board[x+1][y+1].getPiece() instanceof NoPiece == false)
                {
                    moves.add(new Cell(board[x+1][y+1]));
                }
            }

            //sol capraz
            if (x+1 < 8 && y-1 >= 0 && board[x+1][y-1].getPiece().getColor() == false)
            {
                if (board[x+1][y-1].getPiece() instanceof NoPiece == false)
                {
                    moves.add(new Cell(board[x+1][y-1]));
                }
            }
        }
        else if (getColor() == false)
        {
            if (firstMove == true)
            {
                if (board[x-2][y].getPiece() instanceof NoPiece)
                {
                    moves.add(new Cell(board[x-2][y]));
                }
            }

            //yukarı
            if (x-1 >= 0 && board[x-1][y].getPiece() instanceof NoPiece)
            {
                moves.add(new Cell(board[x-1][y]));
            }

            //sag capraz
            if (x-1 >= 0 && y-1 >= 0 && board[x-1][y-1].getPiece().getColor() == true)
            {
                if (board[x-1][y-1].getPiece() instanceof NoPiece == false)
                {
                    moves.add(new Cell(board[x-1][y-1]));
                }
            }

            //sol capraz
            if (x-1 >= 0 && y+1 < 8 && board[x-1][y+1].getPiece().getColor() == true)
            {
                if (board[x-1][y+1].getPiece() instanceof NoPiece == false)
                {
                    moves.add(new Cell(board[x-1][y+1]));
                }
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
