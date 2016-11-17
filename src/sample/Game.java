package sample;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan on 14.11.2016.
 */
public class Game {

    Cell[][] board;

    public Game(){
        this.initBoard();
        //this.printBoard();
    }

    //Bunu kaydededilmiş hali diye düşündüm
    public Game(Game game){
        int i, j;

        Cell temp = null;

        for(j=0; j<8; j++){
            for(i=0; i<8; i++){
                temp.setCell(board[i][j]);
            }
        }

    }

    public void initBoard(){
        int i; int j;

        //piyonların renklerini belirledim ve piyonları yerleştirdim
        boolean color = false;
        for(j=1; j<7; j=j+5) {
            for (i = 0; i < 8; i++) {
                try
                {
                    Pieces piece = new Pawn();
                    board[i][j].setPiece(piece);
                    board[i][j].piece.setColor(color);

                }catch(Exception e)
                {
                    //System.out.println("Null Pointer ");
                }
            }
            color =true;
        }

        //Bos hucreleri olusturdum
        for(j=2; j<6; j++) {
            for (i=0; i<8; i++){
                try
                {
                    //boş hücreler //boş hücrenin color u ne olacak
                    Pieces piece = new NoPiece();
                    board[i][j].setPiece(piece);
                    board[i][j].piece.setColor(true); //şimdilik true atadım

                }catch(Exception e)
                {
                    //System.out.println("Null Pointer ");
                }

            }
        }

        //Ozel taslar icin renk belirledim, ust beyaz, alt siyah
        color =false;
        for(j=0; j<8; j=j+7) {
            for (i=0; i < 8; i++) {
                try
                {
                    board[i][j].piece.setColor(color);

                }catch(Exception e)
                {
                    //System.out.println("Null Pointer ");
                }
            }
            color = true;
        }

        //özel taşlar
        try
        {
            Pieces piece = new Rook();
            //Siyah, alt, Rook
            board[0][0].setPiece(piece);
            board[0][7].setPiece(piece);

            //Beyaz, üst, Rook
            board[7][0].setPiece(piece);
            board[7][7].setPiece(piece);

            piece = new Knight();
            //Siyah, alt, Knight
            board[0][1].setPiece(piece);
            board[0][6].setPiece(piece);

            //Beyaz, üst, Knight
            board[7][1].setPiece(piece);
            board[7][6].setPiece(piece);

            piece = new Bishop();
            //Siyah, alt, Bishop
            board[0][2].setPiece(piece);
            board[0][5].setPiece(piece);

            //Beyaz, üst, Bishop
            board[7][2].setPiece(piece);
            board[7][5].setPiece(piece);

            piece = new King();
            //Siyah, alt, King
            board[0][3].setPiece(piece);

            //Beyaz, üst, King
            board[7][3].setPiece(piece);

            piece = new Queen();
            //Siyah, alt, Queen
            board[0][4].setPiece(piece);

            //Beyaz, üst, Queen
            board[7][4].setPiece(piece);

        }catch(Exception e)
        {
            //System.out.println("Null Pointer ");
        }
    }

    //Burada arrayi nasıl yazacağımı düşünemedim, ekrana basmak için somut bi şey olması lazım ama
    //benim elimde şimdilik somut bi şey yok kontrol ede ede mi gittim ama bilemedim tam
    // 0 -> noPiece
    // Beyaz             Siyah
    // 1 -> wpawn        -1 -> pawn
    // 2 -> wrook        -2 -> rook
    // 3 -> wknight      -3 -> knight
    // 4 -> wbishop      -4 -> bishop
    // 5 -> wking        -5 -> king
    // 6 -> wqueen       -6 -> queen
    public void printBoard(){
        try
        {
            //System.out.println("PrintBoard, try!!");
            int i, j;

            for(j=7; j>=0; j--){
                for(i=7; i>=0; i--){
                    if(board[i][j].piece.getColor() == false)
                    {
                        System.out.println("PrintBoard, false, siyah!!");
                        if(board[i][j].getPiece() instanceof Pawn){
                            System.out.print("-1");
                        }
                        else if(board[i][j].getPiece() instanceof Rook){
                            System.out.print("-2");
                        }
                        else if(board[i][j].getPiece() instanceof Knight){
                            System.out.print("-3");
                        }
                        else if(board[i][j].getPiece() instanceof Bishop){
                            System.out.print("-4");
                        }
                        else if(board[i][j].getPiece() instanceof King){
                            System.out.print("-5");
                        }
                        else if(board[i][j].getPiece() instanceof Queen){
                            System.out.print("-6");
                        }
                    }
                    else if(board[i][j].piece.getColor() == true)
                    {
                        //System.out.println("PrintBoard, true, beyaz!!");
                        if(board[i][j].getPiece() instanceof Pawn){
                            System.out.print(" 1");
                        }
                        else if(board[i][j].getPiece() instanceof Rook){
                            System.out.print(" 2");
                        }
                        else if(board[i][j].getPiece() instanceof Knight){
                            System.out.print(" 3");
                        }
                        else if(board[i][j].getPiece() instanceof Bishop){
                            System.out.print(" 4");
                        }
                        else if(board[i][j].getPiece() instanceof King){
                            System.out.print(" 5");
                        }
                        else if(board[i][j].getPiece() instanceof Queen){
                            System.out.print(" 6");
                        }
                    }
                    else
                        System.out.print(" 0");
                }
                System.out.println();
            }

        }catch(Exception e)
        {
            //System.out.println("Try a giriyor ama ekrana bi şey yazmıyor ya!");
        }
    }

    //henüz yazılmadı
    public int playUser(){
        return 0;
    }

    //henüz yazılmadı
    public int playComputer(){
        return 0;
    }
}
