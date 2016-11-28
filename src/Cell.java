import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Selman Ahatlı on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class Cell implements Serializable{

    private int x;
    private int y;
    public Pieces piece;

    public Cell(){
        x = -1;
        piece = new NoPiece();
        y = -1;
    }

    public Cell(int x, int y, Pieces piece) {
        this.x = x;
        this.y = y;
        this.piece=piece;
        this.piece.setColor(piece.getColor());
    }

    public Cell(Cell cell){
        this.x = cell.getX();
        this.y = cell.getY();
        this.piece = cell.getPiece();
        this.piece.setColor(cell.getPiece().getColor());
    }

    public Pieces getPiece(){
        return piece;
    }

    public void setPiece(Pieces p){
        this.piece = p;
        this.piece.setColor(p.getColor());
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x=x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setCell(Cell cell){
        this.x = cell.getX();
        this.y = cell.getY();
        this.piece = cell.getPiece();
        this.piece.setColor(cell.getPiece().getColor());
    }

    public Cell getCell(){ return this; }

    /**
     *
     * @return koordinatlar(x ve y), piece(color), class(piece turu)
     */
    public String toString(){
        return (x + " " + y + " " + piece.toString() + " " + getClass().toString());
    }

    public boolean equals(Cell obj) {
        if (obj == this)
            return true;

        if(((Cell) obj).getX() == this.getX()){
            if(((Cell) obj).getY() == this.getY()){
                if(((Cell) obj).getPiece() == this.getPiece())
                    return true;
            }
        }
        return false;
    }
}
