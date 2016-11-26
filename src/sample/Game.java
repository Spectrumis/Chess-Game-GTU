package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    private Cell tempCell = new Cell(); //Bu obje play methodunun bir onceki tıklanan buttonu tutabilmesi icin var
    private ArrayList<ArrayList<Cell>> board;
    private Cell[][] removesss; //Geri alma islemleri icin tutulacak Cell arrayi, her yerden ulasilabilsin diye Game classinin bir attribute'u
    private static int counterRemovess = 0; //Geri alma islemi icin sayac

    public Game(){
        removesss = new Cell[64][2];
        board = new ArrayList<ArrayList<Cell>>(8);
        for(int i =0; i < 8; ++i){
            board.add(new ArrayList<Cell>());

            for(int j =0; j < 8; ++j) {
                board.get(i).add(new Cell());
            }
        }
        this.initBoard();
        //this.printBoard();
        this.tempCell = new Cell();
    }

    //Bunu kaydededilmiş hali diye düşündüm
    public Game(Game game){
        board = new ArrayList<ArrayList<Cell>>(8);
        for(int i =0; i < 8; ++i){
            board.add(new ArrayList<Cell>());

            for(int j =0; j < 8; ++j) {
                board.get(i).add(new Cell());
            }
        }
        removesss = new Cell[64][2];

        Cell temp = null;

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                temp.setCell(board.get(i).get(j));
            }
        }
    }

    /**
     * Kullanicinin son tiklamasini alir ve eger depolanmis bisey yoksa ve tiklamasi bos veya karsi takimin tasiysa
     * 0 return eder. Eger bi tasa tiklandiysa 1 return eder ve gidebilecegi yerleri TempMovesList'de depolar
     * Depolanmis yerlerden birine tiklandiginda ise 2 return eder ve oynanicak tasin yerini ve oynancak celli
     * TempMovesList'de depolar
     * Hicbir sey yapilmadiysa da -1 return eder. ( Aslinda bu Exception icin )
     * @param x
     * @param y
     * @param TempMovesList
     * @return
     */
    public int playGame(int x, int y, List<Cell> TempMovesList){
        int status=0;
        try {
            Cell currentCell = new Cell(board.get(x).get(y));
            Cell emptyCell = new Cell();

        /* Verilen cell bos ise veya kendi tasimiz yoksa ... */
            if (currentCell.getPiece() instanceof NoPiece || currentCell.getPiece().getColor() != getCurrentPlayer()) {
            /* ... ve daha once oynatabilecegimiz biseye tiklamadiysak sifir return edicez demektir*/
                if (getTempCell().getX() == -1) {
                    TempMovesList.clear();
                    return 0;
                }
            /* ... ve daha once oynatabilecegimiz bir tasa tikladiysak ...*/
                else {
                /* ... tiklanan yer TempMoveList'te yani oynanabilir hamlelerde varmi diye bakiyoruz */
                /* eger var ise hamlemizi yapiyoruz ve listemizi temizleyip icine hamle source ve targetini atiyoruz*/
                    if (playUser(TempMovesList, currentCell)) {
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
            } else {
            /* Verilen cellde bi tas varsa TempMoveList'imize tasin oynayabilecegi yerlerin listesi aticaz ve 1
            return edicez ve kullanicinin targeti secmesini beklemek uzere beklemeye gecicez */

                tempCell.setCell(currentCell);
                TempMovesList = board.get(x).get(y).getPiece().checkMove(board, x, y);
                status = Main.printCellList(TempMovesList);
                System.out.print("status:" + status + "\n");
                return 1;

            }
        } catch (NullPointerException e){
            return -1;
        }

        //ONEMLI: Her hamlenin kaydedilmesi gerekir, bir arraye.
        //Bu sekilde geri al butonuna basildiginda hamleleri burdan bakarak geri alabiliriz
        //Geri alinan her hamle de bu arrayden silinmeli
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

        board.get(target.getX()).get(target.getY()).setPiece(source.getPiece());

        Pieces piece = new NoPiece();
        board.get(source.getX()).get(source.getY()).setPiece(piece);

        //yapilan hamle arraye kaydedildi, kaynak cell ve source cell olarak!
        removesss[removesss.length][0].setCell(source);
        removesss[removesss.length][1].setCell(target);

    }

    /**
     * Computer icin , easy mod secildiginde bu fonksiyon cagrilacak
     * Method icinde currentPlayer degistirilir
     *
     * tum board tek tek dolasilir.
     * boardin uzerinde player ile aynı renge sahip taslar bulundugunda
     * bu tasin olasi yapabilecegi tum hamleler bir listde tutulur
     * daha sonra random sayi olusturularak bu listden bir hamlenin
     * sirasi secilir ve return listi 2 eleman icerir.
     * ilk elemani source'dur.ikinci elemani target'tir.
     * Lutfen bu methodu kullanicak arkadaslar bu sirayi
     * dikkate aliniz.
     *
     * @return source ve targetin sirali olarak bulundugu bir cell listesi
     */
    public List<Cell> playComputerEasy() {
        boolean player = getCurrentPlayer();
        List<Cell> canMove = new LinkedList<>();
        List<Cell> trgtMove = new LinkedList<>();
        List<Cell> srcMove = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(player == board.get(i).get(j).getPiece().getColor()){
                    if(board.get(i).get(j).getPiece() instanceof Pawn){
                        Pawn wp = new Pawn();
                        canMove.addAll(wp.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                    else if(board.get(i).get(j).getPiece() instanceof Rook){
                        Rook wr = new Rook();
                        canMove.addAll(wr.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                    else if(board.get(i).get(j).getPiece() instanceof Knight){
                        Knight wkn = new Knight();
                        canMove.addAll(wkn.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                    else if(board.get(i).get(j).getPiece() instanceof Bishop){
                        Bishop wb = new Bishop();
                        canMove.addAll(wb.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                    else if(board.get(i).get(j).getPiece() instanceof King){
                        King wki = new King();
                        canMove.addAll(wki.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                    else if(board.get(i).get(j).getPiece() instanceof Queen){
                        Queen wq = new Queen();
                        canMove.addAll(wq.checkMove(board, i, j));
                        for (int k = 0; k < canMove.size(); k++) {
                            srcMove.add(new Cell(board.get(i).get(j)));
                        }
                        trgtMove.addAll(canMove);
                        canMove=null;
                    }
                }
            }
        }
        Random randomGenerator = new Random();
        List<Cell> retList = new ArrayList<>();
        int randomInt = randomGenerator.nextInt(canMove.size());
        retList.add(srcMove.get(randomInt));
        retList.add(trgtMove.get(randomInt));

        setCurrentPlayer(!player);
        return retList;
    }

    /**
     * Computer icin , medium mod secildiginde bu fonksiyon cagrilacak
     * Method icinde currentPlayer degistirilir
     * @return source ve targetin sirali olarak bulundugu bir cell listesi
     */
    public List<Cell> playComputerMedium(){
        return null;
    }

    /**
     * Computer icin , hard mod secildiginde bu fonksiyon cagrilacak
     * Method icinde currentPlayer degistirilir
     * @return source ve targetin sirali olarak bulundugu bir cell listesi
     */
    public List<Cell> playComputerHard(){
        return null;
    }

    /**
     * kapatma dugmesine basildiginda oyunu kaydetmek icin yazilacak fonksiyon
     */
    public void saveGame(){
        //Belirlenen bir dosyaya sifreli sekilde tahtanın son durumu kaydedilir

        try {
            //dosya classlar ile aynı director icinde olusturuluyor
            File file = new File("/Users/Desktop/Chess-Game-GTU/Chess-Game-GTU/src/sample/saveGame.txt");

            // dosya yoksa olusturuldu
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            //tahtanin son hali dosyaya yazildi
            // hucre\nhucre\n......\nhucre\n\n -> 1 row bu sekilde yaziliyor sonra iki \n sonra diger row!
            for(int i=0; i<8; i++) {
                for (int j = 0; j < 8; j++)
                    bw.write(board.get(i).get(j).toString() + "\n");
                bw.write("\n");
            }
            bw.write("\n");
            // board dan sonra 3 tane \n sonra removess

            //hucre hucre \n sonra diger row
            for(int i=0; i<removesss.length; i++) {
                for (int j = 0; j < 2; j++)
                    bw.write(removesss[i][j].toString() + " ");
                bw.write("\n");
            }
            bw.write("\n");

            // son olarak da counterRemovess dosyaya yazilir
            bw.write(counterRemovess);

            // dosya kapatilir
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * oyuna tekrar baslanmak istendiginde yazilacak kod
     */
    public void restartGame(){
        int i,j;
        //tahta ilk haline getirildi
        this.initBoard();

        //hamlelerin tutuldugu array silindi, tekrar oluşturuldu.
        removesss = null;
        removesss = new Cell[64][2];

        //geri alinan hamle sayisi da sifirlandi
        counterRemovess=0;
    }

    /**
     * Geri alma islemine basildiginda en son yapilan hamleyi geri alacak
     * removesss in 2. sutunundaki cell den 1. sutunundaki Cell hareket edilir
     * ve bu hamle artık yapilmadi varsayilip removesss dan kaldirilir.
     */
    public void recallMove(){
        if(counterRemovess <= 5){
            int index = removesss.length;

            board.get(removesss[index][0].getX()).set(removesss[index][1].getY(), board.get(removesss[index][1].getX()).get(removesss[index][1].getY()));
            board.get(removesss[index][0].getX()).get(removesss[index][1].getY()).setPiece(board.get(removesss[index][1].getX()).get(removesss[index][1].getY()).getPiece());

            //indexteki cell elemanlari silindi.
            removesss[index][0] = null;
            removesss[index][1] = null;

            counterRemovess++;
        }
        else
            System.out.println("INVALID!! You can not go back anymore!!");
    }

    public void initBoard(){
        int i; int j;

        //Bos hucreler
        for(i=0; i < 8; ++i){
            for(j=0; j < 8; ++j){
                try
                {
                    //boş hücreler //boş hücrenin color u ne olacak
                    Pieces piece = new NoPiece();
                    board.get(i).get(j).setPiece(piece);
                    board.get(i).get(j).piece.setColor(true); //şimdilik true atadım
                    board.get(i).get(j).setX(i);
                    board.get(i).get(j).setY(j);
                }catch(Exception e)
                {
                    //System.out.println("Null Pointer ");
                }
            }
        }

        //piyonların renklerini belirledim ve piyonları yerleştirdim
        boolean color = false; //siyah
        for(j=1; j<7; j=j+5) {
            for (i = 0; i < 8; i++) {
                try {
                    Pieces piece = new Pawn();
                    board.get(i).get(j).setPiece(piece);
                    board.get(i).get(j).piece.setColor(color);
                } catch(Exception e){

                }
            }
            color =true;
        }

        //özel taşlar
        try {
            //Siyah, alt, Rook
            {
                Pieces piece = new Rook();
                board.get(0).get(0).setPiece(piece);
            }
            {
                Pieces piece = new Rook();
                board.get(7).get(0).setPiece(piece);
            }
            //Beyaz, üst, Rook
            {
                Pieces piece = new Rook();
                board.get(0).get(7).setPiece(piece);
            }
            {
                Pieces piece = new Rook();
                board.get(7).get(7).setPiece(piece);
            }
            //Siyah, alt, Knight
            {
                Pieces piece = new Knight();
                board.get(1).get(0).setPiece(piece);
            }
            {
                Pieces piece = new Knight();
                board.get(6).get(0).setPiece(piece);
            }
            //Beyaz, üst, Knight
            {
                Pieces piece = new Knight();
                board.get(1).get(7).setPiece(piece);
            }
            {
                Pieces piece = new Knight();
                board.get(6).get(7).setPiece(piece);
            }
            //Siyah, alt, Bishop
            {
                Pieces piece = new Bishop();
                board.get(2).get(0).setPiece(piece);
            }
            {
                Pieces piece = new Bishop();
                board.get(5).get(0).setPiece(piece);
            }
            //Beyaz, üst, Bishop
            {
                Pieces piece = new Bishop();
                board.get(2).get(7).setPiece(piece);
            }
            {
                Pieces piece = new Bishop();
                board.get(5).get(7).setPiece(piece);
            }
            //Siyah, alt, King
            {
                Pieces piece = new King();
                board.get(3).get(0).setPiece(piece);
            }
            //Beyaz, üst, King
            {
                Pieces piece = new King();
                board.get(3).get(7).setPiece(piece);
            }
            //Siyah, alt, Queen
            {
                Pieces piece = new Queen();
                board.get(4).get(0).setPiece(piece);
            }
            //Beyaz, üst, Queen
            {
                Pieces piece = new Queen();
                board.get(4).get(7).setPiece(piece);
            }
        }catch(Exception e)
        {
            //System.out.println("Null Pointer ");
        }

        //Ozel taslar icin renk belirledim, ust beyaz, alt siyah
        color = false;
        for(j=0; j<8; j=j+7) {
            for (i=0; i < 8; i++) {
                //System.out.printf("%d ,%d ", i, j);
                board.get(i).get(j).piece.setColor(color);
                //System.out.print(board.get(i).get(j).piece.getColor());
                //System.out.println();
            }
            color = true;
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
        for(int j=7; j>=0; j--) {
            for (int i = 7; i >= 0; i--) {
                if (!board.get(i).get(j).piece.getColor()) {
                    //System.out.println("PrintBoard, false, siyah!!");
                    if (board.get(i).get(j).getPiece() instanceof Pawn) {
                        System.out.print(" P");
                    } else if (board.get(i).get(j).getPiece() instanceof Rook) {
                        System.out.print(" K");
                    } else if (board.get(i).get(j).getPiece() instanceof Knight) {
                        System.out.print(" A");
                    } else if (board.get(i).get(j).getPiece() instanceof Bishop) {
                        System.out.print(" F");
                    } else if (board.get(i).get(j).getPiece() instanceof King) {
                        System.out.print(" S");
                    } else if (board.get(i).get(j).getPiece() instanceof Queen) {
                        System.out.print(" V");
                    } else
                        System.out.print(" .");
                } else if (board.get(i).get(j).piece.getColor()) {
                    //System.out.println("PrintBoard, true, beyaz!!");
                    if (board.get(i).get(j).getPiece() instanceof Pawn) {
                        System.out.print("-P");
                    } else if (board.get(i).get(j).getPiece() instanceof Rook) {
                        System.out.print("-K");
                    } else if (board.get(i).get(j).getPiece() instanceof Knight) {
                        System.out.print("-A");
                    } else if (board.get(i).get(j).getPiece() instanceof Bishop) {
                        System.out.print("-F");
                    } else if (board.get(i).get(j).getPiece() instanceof King) {
                        System.out.print("-S");
                    } else if (board.get(i).get(j).getPiece() instanceof Queen) {
                        System.out.print("-V");
                    } else
                        System.out.print(" .");
                }
            }
            System.out.println();
        }
    }

    public static void setCurrentPlayer( boolean cPlayer ) { currentPlayer = cPlayer; }
    public static boolean getCurrentPlayer() { return currentPlayer; }

    public static void setIsComputerOn( int isOn ) { isComputerOn = isOn; }
    public static int getIsComputerOn() { return isComputerOn; }

    public void setTempCell( Cell target ) { tempCell.setCell(target); }
    public Cell getTempCell() { return tempCell; }
}