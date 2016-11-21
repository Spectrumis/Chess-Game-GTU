package sample;


import java.util.List;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan, AliEmreBuyukersoy and YasinTuluman.
 */
public class Game {

    private static int isComputerOn = 0; //oyuncu vs computer ilk basta kapalı
    /** Degerler soyle:
     *  0 = No Computer (player vs player)
     *  1 = Easy
     *  2 = Normal
     *  3 = Hard
     */
    private static boolean currentPlayer = true; //true = beyaz beyaz baslar
    private Cell tempCell; //Bu obje play methodunun bir onceki tıklanan buttonu tutabilmesi icin var
    private Cell[][] board;
    //private Cell[][] removesss; //Geri alma islemleri icin tutulacak Cell arrayi, her yerden ulasilabilsin diye Game classinin bir attribute'u
    //private static int counterRemovess = 0; //Geri alma islemi icin sayac

    public Game(){
        //removesss = new Cell[][2];
        this.initBoard();
        //this.printBoard();
        this.tempCell = new Cell();
    }

    //Bunu kaydededilmiş hali diye düşündüm
    public Game(Game game){
        //removesss = new Cell[][2];
        int i, j;

        Cell temp = null;

        for(j=0; j<8; j++){
            for(i=0; i<8; i++){
                temp.setCell(board[i][j]);
            }
        }
    }

    /**
     * Kullanicinin son tiklamasini alir ve eger depolanmis bisey yoksa ve tiklamasi bos veya karsi takimin tasiysa
     * 0 return eder. Eger bi tasa tiklandiysa 1 return eder ve gidebilecegi yerleri TempMovesList'de depolar
     * Depolanmis yerlerden birine tiklandiginda ise 2 return eder ve oynanicak tasin yerini ve oynancak celli
     * TempMovesList'de depolar
     * @param x
     * @param y
     * @param TempMovesList
     * @return
     */
    public int playGame(int x, int y, List<Cell> TempMovesList){
        Cell currentCell = new Cell(board[x][y]);
        Cell emptyCell = new Cell();

        /* Verilen cell bos ise veya kendi tasimiz yoksa ... */
        if(currentCell.getPiece() instanceof NoPiece || currentCell.getPiece().getColor() != getCurrentPlayer() ){
            /* ... ve daha once oynatabilecegimiz biseye tiklamadiysak sifir return edicez demektir*/
            if(getTempCell().getX() == -1){
                TempMovesList.clear();
                return 0;
            }
            /* ... ve daha once oynatabilecegimiz bir tasa tikladiysak ...*/
            else {
                /* ... tiklanan yer TempMoveList'te yani oynanabilir hamlelerde varmi diye bakiyoruz */
                /* eger var ise hamlemizi yapiyoruz ve listemizi temizleyip icine hamle source ve targetini atiyoruz*/
                if(playUser(TempMovesList, currentCell)){
                    makeMove(getTempCell(), currentCell);
                    TempMovesList.clear();
                    TempMovesList.add(getTempCell());
                    TempMovesList.add(currentCell);
                    setTempCell(emptyCell);
                    /* suanki oyuncu degerini degistiriyoruz */
                    setCurrentPlayer(!getCurrentPlayer());
                    /* ve 2 komutunu return ederek hamle yaptik diyoruz */
                    return 2;
                }
                /* eger tiklanan yer listede yoksa TempMovesListesini temizleyip sifir return ediyoruz */
                else {
                    TempMovesList.clear();
                    setTempCell(emptyCell);
                    return 0;
                }
            }
        }
        else{
            /* Verilen cellde bi tas varsa TempMoveList'imize tasin oynayabilecegi yerlerin listesi aticaz ve 1
            return edicez ve kullanicinin targeti secmesini beklemek uzere beklemeye gecicez */

            tempCell.setCell(currentCell);
            TempMovesList = board[x][y].getPiece().checkMove(board, x,y);
            return 1;

        }


        //Help'e bu komutlar düzenlenerek yazilabilir!!!!!!!!!!!
        // 1. computer-user mi user-user mi seçildi diye sor
        // 2. eger computer ile oynama secildiyse
            // 1. kolay-orta-zor levellerinden hangisinin secildigini sor
                //kolaysa 1 hamle ilerisine bak (Bakilacak hamle sayilari degisebilir)
                //ortaysa 2 hamle ilerisine bak
                //zorsa 3 hamle ilerisine bak
            // ilk computer baslar (Beyaz), rastgele bir tas acar
            // Computer in oynamasi icin ilk basta start dugmesine basilmali
        // 3. eger user-user secildiyse
            //zaten direkt insanların sectigi hamleyi oynayacaksin

        //Surekli kapatma dugmesine basilip basilmadigini kontrol et
        // basildiysa
            // save edilmek istendiyse
                //Sifreli bir sekilde kaydet
            //istenmediyse
                //cik, ekrani kapat
        //

        //Restart yapildi mi diye bak
            //yapildiysa oyunu sifirla

        //ONEMLI: Her hamlenin kaydedilmesi gerekir, bir arraye.
        //Bu sekilde geri al butonuna basildiginda hamleleri burdan bakarak geri alabiliriz
        //Geri alinan her hamle de bu arrayden silinmeli
        // Cell[][] removesss = new Cell[][2]();
        // her satirda source ve target bulunacak
    }


    /**
     * User-User secildiginde bu fonksiyon cagrilacak
     * Bu fonksiyon sadece user dan gelen hamle dogru ise hareket ettirir, degilse hatali
     * secim yaptigini soyler ve tekrar secim yapmasini ister
     * Bu fonksiyon computer-user secildiginde de kullanilacak, böylelikle user hamleleri kontrol edilecek
     * Eger secim dogruysa 1 yanlissa 0 return eder
     * @return
     */
    public boolean playUser(List<Cell> cellList, Cell cell ) {

        for (Cell searched : cellList) {
            if (searched == cell)
                return true;
        }
        return false;
    }

    /**
     * board uzerinde degisiklik yapicak olan methodumuz. Source daki tasimizi target'a tasiyacak
     * @param source
     * @param target
     */
    public void makeMove(Cell source, Cell target){

    }

    /**
     * Computer icin , easy mod secildiginde bu fonksiyon cagrilacak
     * Method icinde source ve targetin sirali olarak bulundugu bir cell listesi return eder
     * ve currentPlayeri degistirir
     * @return
     */
    public List<Cell> playComputerEasy() { return 0; }

    /**
     * Computer icin , medium mod secildiginde bu fonksiyon cagrilacak
     * Method icinde source ve targetin sirali olarak bulundugu bir cell listesi return eder
     * ve currentPlayeri degistirir
     * @return
     */
    public List<Cell> playComputerMedium(){
        return 0;
    }

    /**
     * Computer icin , hard mod secildiginde bu fonksiyon cagrilacak
     * Method icinde source ve targetin sirali olarak bulundugu bir cell listesi return eder
     * ve currentPlayeri degistirir
     * @return
     */
    public List<Cell> playComputerHard(){
        return 0;
    }

    /**
     * kapatma dugmesine basildiginda oyunu kaydetmek icin yazilacak fonksiyon
     */
    public void saveGame(){
        //Belirlenen bir dosyaya sifreli sekilde tahtanın son durumu kaydedilir
    }

    /**
     * oyuna tekrar baslanmak istendiginde yazilacak kod
     */
    public void restartGame(){
        //tahtayi ilk haline geri getirip, ekranda gosterir
    }

    /**
     * Geri alma islemine basildiginda geri alacak
     */
    public void recallMove(){
        //counterRemovess <= 5 ise
            //removess arrayine bakilir ve son hamleyi geri alir
            //geri alma islemi max 5 defa olacagi icinde bir counterRemovesss arttirilir.
        // degilse hicbir sey yaapilmaz
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

    /*
      Burada arrayi nasıl yazacağımı düşünemedim, ekrana basmak için somut bi şey olması lazım ama
        benim elimde şimdilik somut bi şey yok kontrol ede ede mi gittim ama bilemedim tam
        0 -> noPiece
        Beyaz             Siyah
        1 -> wpawn        -1 -> pawn
        2 -> wrook        -2 -> rook
        3 -> wknight      -3 -> knight
        4 -> wbishop      -4 -> bishop
        5 -> wking        -5 -> king
        6 -> wqueen       -6 -> queen
    */
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

    public static void setCurrentPlayer( boolean cPlayer ) { currentPlayer = cPlayer; }
    public static boolean getCurrentPlayer() { return currentPlayer; }

    public static void setIsComputerOn( int isOn ) { isComputerOn = isOn; }
    public static int getIsComputerOn() { return isComputerOn; }

    public void setTempCell( Cell target ) { tempCell.setCell(target); }
    public Cell getTempCell() { return tempCell; }
}