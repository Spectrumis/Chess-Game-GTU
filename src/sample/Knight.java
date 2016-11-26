package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created and implemented by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class Knight extends Pieces {
    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        if(!getColor()) { //siraya gore islem yapiyor false = siyah
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && (board.get(x+1).get(y+2).getPiece().getColor() || board.get(x+1).get(y+2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+1).get(y+2)));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && (board.get(x-1).get(y+2).getPiece().getColor() || board.get(x-1).get(y+2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-1).get(y+2)));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && (board.get(x+1).get(y-2).getPiece().getColor()|| board.get(x+1).get(y-2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+1).get(y-2)));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && (board.get(x-1).get(y-2).getPiece().getColor() || board.get(x-1).get(y-2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-1).get(y-2)));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && (board.get(x-2).get(y+1).getPiece().getColor() || board.get(x-2).get(y+1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-2).get(y+1)));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && (board.get(x+2).get(y+1).getPiece().getColor() || board.get(x+2).get(y+1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+2).get(y+1)));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && (board.get(x-2).get(y-1).getPiece().getColor() || board.get(x-2).get(y-1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-2).get(y-1)));
            // 2 sola, 1 yukari
            if(y-1 >= 0 && x+2 < 8 && (board.get(x+2).get(y-1).getPiece().getColor() || board.get(x+2).get(y-1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+2).get(y-1)));
        }
        else if(getColor()) { //siraya gore islem yapiyor true = beyaz
            // 1 saga, 2 yukari
            if (y+2 < 8 && x+1 < 8 && (!board.get(x+1).get(y+2).getPiece().getColor() || board.get(x+1).get(y+2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+1).get(y+2)));
            // 1 saga, 2 asagi
            if(y+2 < 8 && x-1 >= 0 && (!board.get(x-1).get(y+2).getPiece().getColor() || board.get(x-1).get(y+2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-1).get(y+2)));
            // 1 sola, 2 yukari
            if (y-2 >= 0 && x+1 < 8 && (!board.get(x+1).get(y-2).getPiece().getColor() || board.get(x+1).get(y-2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+1).get(y-2)));
            // 1 sola, 2 asagi
            if(y-2 >= 0 && x-1 >= 0 && (!board.get(x-1).get(y-2).getPiece().getColor() || board.get(x-1).get(y-2).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-1).get(y-2)));
            // 2 saga, 1 asagi
            if(y+1 < 8 && x-2 >= 0 && (!board.get(x-2).get(y+1).getPiece().getColor() || board.get(x-2).get(y+1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-2).get(y+1)));
            // 2 saga, 1 yukari
            if(y+1 < 8 && x+2 < 8 && (!board.get(x+2).get(y+1).getPiece().getColor() || board.get(x+2).get(y+1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+2).get(y+1)));
            // 2 sola, 1 asagi
            if(y-1 >= 0 && x-2 >= 0 && (!board.get(x-2).get(y-1).getPiece().getColor() || board.get(x-2).get(y-1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x-2).get(y-1)));
            // 2 sola, 1 yukari
            if(y-1 >= 0 && x+2 < 8 && (!board.get(x+2).get(y-1).getPiece().getColor() || board.get(x+2).get(y-1).getPiece() instanceof NoPiece))
                moves.add(new Cell(board.get(x+2).get(y-1)));
        }
        return moves;
    }

}
