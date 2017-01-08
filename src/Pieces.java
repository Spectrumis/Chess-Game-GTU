import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GozdeDogan on 15.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public abstract class Pieces implements Serializable{

    public boolean color; //tasin rengi true->beyaz false ->siyah oynayan kim
    private boolean isMoved = false;

    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y){
        return null;
    }
    public void setColor(boolean colors){
        color = colors;
    }
    public boolean getColor(){
        return color;
    }

    /**
     *
     * @return color
     */
    public String toString(){

        if(color == true)
            return "true";
        else
            return "false";
    }

    public boolean inDanger(ArrayList<ArrayList<Cell>> board){ return false; }
    public boolean kingInList(List<Cell> moves){ return false; }
    public void setIsMoved(boolean value){ isMoved = value; }

    public boolean getIsMoved(){ return isMoved; }
}
