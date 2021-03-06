import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 */
public class Queen extends Pieces{
    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {

        List<Cell> moves = new LinkedList<>();

        //Ilk olarak secili tasin rengini kiyaslama yapmak icin tutuyorum
        boolean curColor = board.get(x).get(y).getPiece().getColor() ;

        //Sol tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 atadim. Yani tasin hemen soluna bakicam ilk
        for(int i=x-1; i >= 0; --i){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(y).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
                break;
            }
            //Eger ayni renk bi tas varsa uzerine oynayamıyacagindan ve daha ileri de gidemeyeceginden
            //burada move liste daha fazla bise atamadan donguden cikiyorum
            else
                break;
        }

        //Ayni islemleri saga dogru bakiyorum
        for(int i=x+1; i <= 7; ++i){
            if(board.get(i).get(y).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
                break;
            }
            else
                break;
        }

        //Asagiya ve
        curColor = board.get(x).get(y).getPiece().getColor() ;
        for(int i=y-1; i >= 0; --i){
            if(board.get(x).get(i).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(i)));
            }
            else if(board.get(x).get(i).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(i)));
                break;
            }
            else
                break;
        }

        //Yukariya dogru da ayni islemler gecerli
        curColor = board.get(x).get(y).getPiece().getColor() ;
        for(int i=y+1; i <= 7; ++i){
            if(board.get(x).get(i).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(i)));
            }
            else if(board.get(x).get(i).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(i)));
                break;
            }
            else
                break;
        }

        //Sol alt tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 ve j ye y-1 atadim. Yani tasin hemen solu altına bakicam ilk
        int j=y-1;
        for(int i=x-1; (i >= 0) && (j >= 0); --i){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
                break;
            }
            //Eger ayni renk bi tas varsa uzerine oynayamıyacagindan ve daha ileri de gidemeyeceginden
            //burada move liste daha fazla bise atamadan donguden cikiyorum
            else
                break;
            --j;
        }

        //Ayni islemleri sag alta dogru bakiyorum
        j=y-1;
        for(int i=x+1; (i <= 7) && (j >= 0); ++i){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
                break;
            }
            else
                break;
            --j;
        }

        //Sag uste ve
        j=y+1;
        for(int i=x+1; (i <= 7) && (j <= 7); ++i){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
                break;
            }
            else
                break;
            ++j;
        }

        //Sol uste dogru da ayni islemler gecerli
        j=y+1;
        for(int i=x-1; (i >= 0) && (j <= 7); --i){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(j)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
                break;
            }
            else
                break;
            ++j;
        }

        //queenin her yone dogru oynayabilecegi hamleleri move listesine atadik ve donduruyoruz
        return moves;
    }

}
