package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GozdeDogan on 15.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public abstract class Pieces{

    public boolean color; //tasin rengi true->beyaz false ->siyah oynayan kim

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
}
