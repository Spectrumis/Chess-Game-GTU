import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by YasinTulumen on 14.11.2016
 *
 */
public class King extends Pieces {
    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {
        List<Cell> moves = new LinkedList<>();
        int i, j;
        //Ilk olarak secili tasin rengini kiyaslama yapmak icin tutuyorum
        boolean curColor = board.get(x).get(y).getPiece().getColor() ;

        //Sol tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 atadim. Yani tasin hemen soluna bakicam ilk
        i = x-1;
        if(i >= 0){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(y).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        j = y-1;
        if((i>= 0) && (j >= 0)){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
            }
        }
        //Ayni islemleri saga dogru bakiyorum
        i = x+1;
        if(i <= 7){
            if(board.get(i).get(y).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        //Ayni islemleri sag alta dogru bakiyorum
        j = y-1;
        if((i <= 7) && (j >= 0)){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor) {
                moves.add(new Cell(board.get(i).get(j)));
            }
        }
        //Asagiya ve
        if(j >= 0){
            if(board.get(x).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(j)));
            }
            else if(board.get(x).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(j)));
            }
        }

        //Sag uste ve
        j = y+1;
        if((i <= 7) && (j <= 7)){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
            }
        }
        //Yukariya dogru da ayni islemler gecerli
        if(j <= 7){
            if(board.get(x).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(j)));
            }
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(j)));
            }
        }
        //Sol uste dogru da ayni islemler gecerli
        i = x-1;
        if((i >= 0) && (j <= 7)){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
            }
        }
        //Kalenin her yone dogru oynayabilecegi hamleleri move listesine atadik ve donduruyoruz
        return moves;
    }

    public boolean inDanger(ArrayList<ArrayList<Cell>> board)
    {
        for (int i=0 ; i<7 ; ++i)
        {
            for (int j=0 ; j<7 ; ++j)
            {
                if (!(board.get(i).get(j).getPiece() instanceof NoPiece))
                {
                    if (getColor() != board.get(i).get(j).getPiece().getColor())
                    {
                        List<Cell> moves = new LinkedList<>();

                        moves = board.get(i).get(j).getPiece().checkMove(board,i,j);

<<<<<<< HEAD
                        if(kingInList(moves))
=======
                        if(true)
>>>>>>> master
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean kingInList(List<Cell> moves)
    {
        for (int i=0 ; i<moves.size() ; ++i)
        {
            if(moves.get(i).getPiece() instanceof King)
            {
                return true;
            }
        }

        return false;
    }
}
