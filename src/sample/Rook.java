package sample;

import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 */
public class Rook extends Pieces {
    @Override
    public List<Cell> check(Cell[][] board, int x, int y) {
        return null;
    }

    @Override
    public void setColor(boolean colors) {

    }

    @Override
    public boolean getColor() {
        return color;
    }
}
