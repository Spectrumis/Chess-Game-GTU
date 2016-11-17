package sample;

import java.util.List;

/**
 * Created by Selman Ahatlı on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public interface Piece {

    //public boolean color = false; //taşın rengi true->beyaz false ->siyah oynayan kim

    public List<Cell> checkMove(Cell[][] board, int x, int y);
    public void setColor(boolean colors);
    public boolean getColor();
}
