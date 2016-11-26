package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Selman Ahatlı on 14.11.2016.
 * Implemented by Selman Ahatlı on 14.11.2016.
 */
public class Pawn extends Pieces {

    private boolean firstMove = true;

    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if (getColor())
        {
            if (firstMove)
            {
                if (board.get(x+2).get(y).getPiece() instanceof NoPiece)
                {
                    moves.add(new Cell(board.get(x+2).get(y)));
                }
            }

            //yukarı
            if (x+1 < 8 && board.get(x+1).get(y).getPiece() instanceof NoPiece)
            {
                moves.add(new Cell(board.get(x+1).get(y)));
            }

            //sag capraz
            if (x+1 < 8 && y+1 < 8 && !board.get(x+1).get(y+1).getPiece().getColor())
            {
                if (!(board.get(x + 1).get(y + 1).getPiece() instanceof NoPiece))
                {
                    moves.add(new Cell(board.get(x+1).get(y+1)));
                }
            }

            //sol capraz
            if (x+1 < 8 && y-1 >= 0 && !board.get(x + 1).get(y - 1).getPiece().getColor())
            {
                if (!(board.get(x + 1).get(y - 1).getPiece() instanceof NoPiece))
                {
                    moves.add(new Cell(board.get(x+1).get(y-1)));
                }
            }
        }
        else if (!getColor())
        {
            if (firstMove)
            {
                if (board.get(x-2).get(y).getPiece() instanceof NoPiece)
                {
                    moves.add(new Cell(board.get(x-2).get(y)));
                }
            }

            //yukarı
            if (x-1 >= 0 && board.get(x-1).get(y).getPiece() instanceof NoPiece)
            {
                moves.add(new Cell(board.get(x-1).get(y)));
            }

            //sag capraz
            if (x-1 >= 0 && y-1 >= 0 && board.get(x-1).get(y-1).getPiece().getColor())
            {
                if (!(board.get(x-1).get(y-1).getPiece() instanceof NoPiece))
                {
                    moves.add(new Cell(board.get(x-1).get(y-1)));
                }
            }

            //sol capraz
            if (x-1 >= 0 && y+1 < 8 && board.get(x-1).get(y+1).getPiece().getColor())
            {
                if (!(board.get(x - 1).get(y + 1).getPiece() instanceof NoPiece))
                {
                    moves.add(new Cell(board.get(x-1).get(y+1)));
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
