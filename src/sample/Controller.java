package sample;

public class Controller {
    // 0 -> noPiece
    // 1 -> pawn
    // 2 -> rook
    // 3 -> knight
    // 4 -> queen
    // 5 -> bishop
    // 6 -> king
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
