import javafx.scene.control.Button;

/**
 * Created by Recep Sivri on 26.11.2016.
 */

public class ExtendedButton extends Button {

    private Coordinate coor=new Coordinate(0,0);

    public void setCoor(Coordinate coor) {
        this.coor.setX(coor.getX());
        this.coor.setY(coor.getY());
    }
    public int getCoorX()
    {
        return coor.getX();
    }
    public int getCoorY()
    {
        return coor.getY();
    }
}