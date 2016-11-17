package sample;

/**
 * Implemented by GozdeDogan on 14.11.2016.
 */

public class Controller {
    // 0 -> noPiece
    // Beyaz             Siyah
    // 1 -> wpawn        -1 -> pawn
    // 2 -> wrook        -2 -> rook
    // 3 -> wknight      -3 -> knight
    // 4 -> wbishop      -4 -> bishop
    // 5 -> wking        -5 -> king
    // 6 -> wqueen       -6 -> queen
    public static boolean isEnemyOfBlack(Cell CurrentButton) {
        if(CurrentButton.piece.getColor() == true)
            return true;
        else
            return false;
    }

    public static boolean isEnemyOfWhite(Cell CurrentButton) {
        if(CurrentButton.piece.getColor() == false)
            return true;
        else
            return false;
    }
}
