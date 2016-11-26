package sample;

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
                moves.add(new Cell(board.get(i).get(y)));
            }
            //Eger bi tas varsa ve ayni renk deilse yiyebilecegim icin move listesine ekliyorum
            //fakat yemekten oteye gecemedigimden hemen ardindan dongumu sonlandiriyorum
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
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
                moves.add(new Cell(board.get(i).get(y)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor) {
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        //Asagiya ve
        if(j >= 0){
            if(board.get(x).get(i).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(i)));
            }
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(i)));
            }
        }

        //Sag uste ve
        j = y+1;
        if((i <= 7) && (j <= 7)){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        //Yukariya dogru da ayni islemler gecerli
        if(j <= 0){
            if(board.get(x).get(i).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(x).get(i)));
            }
            else if(board.get(i).get(y).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(x).get(i)));
            }
        }
        //Sol uste dogru da ayni islemler gecerli
        i = x-1;
        if((i >= 0) && (j >= 0)){
            if(board.get(i).get(j).getPiece() instanceof NoPiece){
                moves.add(new Cell(board.get(i).get(y)));
            }
            else if(board.get(i).get(j).getPiece().getColor() != curColor){
                moves.add(new Cell(board.get(i).get(y)));
            }
        }
        //Kalenin her yone dogru oynayabilecegi hamleleri move listesine atadik ve donduruyoruz
        return moves;
    }

}
