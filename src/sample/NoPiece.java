package sample;

import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 */
public class NoPiece implements Piece{

    public NoPiece(){

    }

    @Override
    public List<Cell> check(Cell[][] board, int x, int y) {
        return null;
    }

    @Override
    public void setColor(boolean colors) {
        setColor(colors);
    }

    @Override
    public boolean getColor() {
        return getColor();
    }
}
