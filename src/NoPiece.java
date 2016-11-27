import java.util.ArrayList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class NoPiece extends Pieces{

    public NoPiece(){

    }

    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {
        return null;
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
