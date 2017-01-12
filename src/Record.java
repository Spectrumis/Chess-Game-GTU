/**
 *
 * Created by yacikgoz on 12.01.2017.
 */
public class Record {
    private int source_x;
    private int source_y;
    private int target_x;
    private int target_y;
    public int getSource_x(){ return source_x; }
    public int getSource_y(){ return source_y; }
    public int getTarget_x(){ return target_x; }
    public int getTarget_y(){ return target_y; }
    public void setSource_x(int x){ source_x = x; }
    public void setSource_y(int y){ source_y = y; }
    public void setTarget_x(int x){ target_x = x; }
    public void setTarget_y(int y){ target_y = y; }
    final String bishop = "♗";
    final String rook = "♖";
    final String pawn = "♙";
    final String queen = "♕";
    final String king = "♔";
    final String knight = "♘";
    public String color;
    public String piece;
    @Override
    public String toString(){
        return color + "" + piece + (char)getSource_x() + getSource_y() + " -> " + (char)getTarget_x() + getTarget_y()+ "\n";
    }

}
