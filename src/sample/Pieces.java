package sample;

import java.util.List;

/**
 * Created by GozdeDogan on 15.11.2016.
 */
public abstract class Pieces implements Piece{

    public static boolean color = false; //taþýn rengi true->beyaz false ->siyah oynayan kim

    public List<Cell> check(Cell[][] board,int x,int y){
        return null;
    }
    public void setColor(boolean colors){
        color = colors;
    }
    public boolean getColor(){
        return color;
    }
}
