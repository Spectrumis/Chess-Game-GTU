/**
 * Created by Recep Sivri on 28.11.2016.
 */
public class Table {//BU Classı geri alma özelliğini imlement edebilmek için yazdım.
    public ExtendedButton [][] Table;
    public Table(ExtendedButton[][] Game)
    {
        int i,j;
        Table= new ExtendedButton[8][];
        for(i=0;i<8;++i)
        {
            Table[i] = new ExtendedButton[8];
            for (j = 0; j < 8; ++j) {
                Table[i][j] = new ExtendedButton();
                Table[i][j].setMinSize(80, 80);

                Table[i][j].setStyle(Game[i][j].getStyle());
                Table[i][j].setCoor(new Coordinate(i, j));
            }
        }
    }
}