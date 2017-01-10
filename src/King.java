import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by YasinTulumen on 14.11.2016
 *
 */
public class King extends Pieces {
    private boolean isMoved = false;

    @Override
    public List<Cell> checkMove(ArrayList<ArrayList<Cell>> board, int x, int y) {
        List<Cell> moves = new LinkedList<>();
        int i, j;
        //Ilk olarak secili tasin rengini kiyaslama yapmak icin tutuyorum
        boolean curColor = board.get(x).get(y).getPiece().getColor() ;

        // SOL TARAFA BAKIYORUZ
        if(x-1 >= 0 && (board.get(x-1).get(y).getPiece() instanceof NoPiece || board.get(x-1).get(y).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x-1).get(y)));
        // SAG TARAFA BAKIYORUZ
        if(x+1 <= 7 && (board.get(x+1).get(y).getPiece() instanceof NoPiece || board.get(x+1).get(y).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x+1).get(y)));
        // ALT TARAFA BAKIYORUZ
        if(y-1 >= 0 && (board.get(x).get(y-1).getPiece() instanceof NoPiece || board.get(x).get(y-1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x).get(y-1)));
        // UST TARAFA BAKIYORUZ
        if(y+1 <= 7 && (board.get(x).get(y+1).getPiece() instanceof NoPiece || board.get(x).get(y+1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x).get(y+1)));


        // SOL ALT TARAFA BAKIYORUZ
        if(x-1 >= 0 && y-1 >= 0 && (board.get(x-1).get(y-1).getPiece() instanceof NoPiece || board.get(x-1).get(y-1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x-1).get(y-1)));
        // SOL UST TARAFA BAKIYORUZ
        if(x-1 >= 0 && y+1 <=7 && (board.get(x-1).get(y+1).getPiece() instanceof NoPiece || board.get(x-1).get(y+1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x-1).get(y+1)));
        // SAG ALT TARAFA BAKIYORUZ
        if(x+1 <= 7 && y-1 >= 0 && (board.get(x+1).get(y-1).getPiece() instanceof NoPiece || board.get(x+1).get(y-1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x+1).get(y-1)));
        // SAG UST TARAFA BAKIYORUZ
        if(x+1 <= 7 && y+1 <= 7 && (board.get(x+1).get(y+1).getPiece() instanceof NoPiece || board.get(x+1).get(y+1).getPiece().getColor() != curColor))
            moves.add(new Cell(board.get(x+1).get(y+1)));

        /* Rook hamlesi için olan bolum
        if(!this.getIsMoved()){
            if(board.get(x-1).get(y).getPiece() instanceof NoPiece){
                if(board.get(x-2).get(y).getPiece() instanceof NoPiece){
                    if((board.get(x-3).get(y).getPiece() instanceof Rook) && !board.get(x-3).get(y).getPiece().getIsMoved()){
                        moves.add(new Cell(board.get(x-2).get(y)));
                    }
                }
            }
            if(board.get(x+1).get(y).getPiece() instanceof NoPiece){
                if(board.get(x+2).get(y).getPiece() instanceof NoPiece){
                    if(board.get(x+3).get(y).getPiece() instanceof NoPiece){
                        if((board.get(x+4).get(y).getPiece() instanceof Rook) && !board.get(x+4).get(y).getPiece().getIsMoved()){
                            moves.add(new Cell(board.get(x+2).get(y)));
                        }
                    }
                }
            }
        }*/
/*
        //Sol tarafina dogru hareket edebilirligine bakarak gidiyorum. Ilk bakacagim
        //yer olarak i ye x-1 atadim. Yani tasin hemen soluna bakicam ilk
        i = x-1; // x-1, y -> SOL
        if(i >= 0){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(y).getPiece() instanceof NoPiece || board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        j = y-1; // x-1, y-1 -> SOL ALT
        if((i>= 0) && (j >= 0)){
            //Eger tasin solu bos ise moves listeme ekleyerek devam ederim
            if(board.get(i).get(j).getPiece() instanceof NoPiece || board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
            }
        }

        //Ayni islemleri saga dogru bakiyorum
        i = x+1; // x+1, y -> SA�
        if(i <= 7){
            if(board.get(i).get(y).getPiece() instanceof NoPiece || board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }

        j=y+1; // x+1, y+1 -> SAG UST
        if(j <= 7){
            if(board.get(i).get(j).getPiece() instanceof NoPiece || board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(j)));
            }
        }

        //Ayni islemleri sag alta dogru bakiyorum
        j = y-1; // x+1, y-1 -> SAG ALT
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

        //Sol uste ve
        i = x-1;
        j = y+1; // x-1, y+1 -> SOL UST
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
        }*/
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

                        if(kingInList(moves))

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

    public void setIsMoved(boolean value){
        isMoved = value;
    }

    public boolean getIsMoved(){
        return isMoved;
    }
}
