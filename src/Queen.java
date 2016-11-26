import java.util.LinkedList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 */
public class Queen extends Pieces{
    @Override
    public List<Cell> checkMove(Cell[][] board, int x, int y) {
        List<Cell> moves = new LinkedList<>();

        //Ilk olarak secili tasin rengini kiyaslama yapmak icin tutuyorum
        boolean curColor = board[x][y].getPiece().getColor() ;

        //Sol tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 atadim. Yani tasin hemen soluna bakicam ilk
        for(int i=x-1; i >= 0; --i){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board[i][y].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board[i][y].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            //Eger ayni renk bi tas varsa uzerine oynayamıyacagindan ve daha ileri de gidemeyeceginden
            //burada move liste daha fazla bise atamadan donguden cikiyorum
            else
                break;
        }
        //Sol alt tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 ve j ye y-1 atadim. Yani tasin hemen solu altına bakicam ilk
        int j=y-1;
        for(int i=x-1; (i >= 0) && (j >= 0); --i){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board[i][j].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board[i][j].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            //Eger ayni renk bi tas varsa uzerine oynayamıyacagindan ve daha ileri de gidemeyeceginden
            //burada move liste daha fazla bise atamadan donguden cikiyorum
            else
                break;
            --j;
        }
        //Ayni islemleri saga dogru bakiyorum
        for(int i=x+1; i <= 7; ++i){
            if(board[i][y].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            else if(board[i][y].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            else
                break;
        }
        //Ayni islemleri sag alta dogru bakiyorum
        j=y-1;
        for(int i=x+1; (i <= 7) && (j >= 0); ++i){
            if(board[i][j].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            else if(board[i][j].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            else
                break;
            --j;
        }
        //Asagiya ve
        curColor = board[x][y].getPiece().getColor() ;
        for(int i=y-1; i >= 0; --i){
            if(board[x][i].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[x][i]));
            }
            else if(board[i][y].getPiece().getColor() != curColor){
                moves.add(new Cell(board[x][i]));
                break;
            }
            else
                break;
        }
        //Sag uste ve
        j=y+1;
        for(int i=x+1; (i <= 7) && (j <= 7); ++i){
            if(board[i][j].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            else if(board[i][j].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            else
                break;
            ++j;
        }
        //Yukariya dogru da ayni islemler gecerli
        curColor = board[x][y].getPiece().getColor() ;
        for(int i=y+1; i >= 0; ++i){
            if(board[x][i].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[x][i]));
            }
            else if(board[i][y].getPiece().getColor() != curColor){
                moves.add(new Cell(board[x][i]));
                break;
            }
            else
                break;
        }
        //Sol uste dogru da ayni islemler gecerli
        j=y+1;
        for(int i=x-1; (i >= 0) && (j >= 0); --i){
            if(board[i][j].getPiece() instanceof NoPiece){
                moves.add(new Cell(board[i][y]));
            }
            else if(board[i][j].getPiece().getColor() != curColor){
                moves.add(new Cell(board[i][y]));
                break;
            }
            else
                break;
            ++j;
        }
        //Kalenin her yone dogru oynayabilecegi hamleleri move listesine atadik ve donduruyoruz
        return moves;
    }

    @Override
    public void setColor(boolean colors) {

    }

    @Override
    public boolean getColor() {
        return color;
    }
}
