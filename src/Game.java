import java.io.*;
import java.util.*;

/**
 * Created by GozdeDogan on 14.11.2016.
 * Implemented by GozdeDogan, AliEmreBuyukersoy and YasinTulumen.
 */
public class Game implements Serializable {

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
    private ArrayList<ArrayList<Cell>> fakeBoard;

    private Stack<Cell> removesss; //Geri alma islemleri icin tutulacak Cell arrayi, her yerden ulasilabilsin diye Game classinin bir attribute'u
    private static int counterRemovess = 0; //Geri alma islemi icin sayac
    /**
     * bu degerler minimax algoritmasi icin kullanilacak
     */
    private static final double PAWN = 1.0;
    private static final double KNIGHT = 3.2;
    private static final double BISHOP = 3.33;
    private static final double ROOK = 5.1;
    private static final double QUEEN = 8.8;
    private static final double KING = Double.POSITIVE_INFINITY;



    /**
     *
     * @return
     */
    public ArrayList<ArrayList<Cell>> getBoard()
    {
        return board;
    }

    /**
     *
     */
    public Game(){
        removesss = new Stack<Cell>();
        for(int i =0; i < 64*2; ++i) {
            removesss.add(new Cell());
        }

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

    /**
     * kaydededilmiş hali diye düşündüm
     * @param game
     */
    public Game(Game game){
        board = new ArrayList<ArrayList<Cell>>(8);
        for(int i =0; i < 8; ++i){
            board.add(new ArrayList<Cell>());

            for(int j =0; j < 8; ++j) {
                board.get(i).add(new Cell());
            }
        }
        removesss = new Stack<Cell>();
        for(int i =0; i < 64*2; ++i) {
            removesss.add(new Cell());
        }

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                board.get(i).get(j).setCell(game.board.get(i).get(j));
            }
        }

        for(int i=0; i<game.removesss.size()*2; i++){
                removesss.get(i).setCell(game.removesss.get(i));
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
            int king;

            king = kingPosition(getCurrentPlayer());
            //oyun sonu geldiyse 3 dondurur
            if(isEnd()){
                return 3;
            }
            //burada sah tehlikedemi diye bakar
            if(board.get(king/10).get(king-(king/10)*10).getPiece().inDanger(board)){
                if(x != king/10 || y != king-(king/10)*10){
                    return 0;
                }
            }
        /* Verilen cell bos ise veya kendi tasimiz yoksa ... */
            if (currentCell.getPiece() instanceof NoPiece || currentCell.getPiece().getColor() != getCurrentPlayer()) {
            /* ... ve daha once oynatabilecegimiz biseye tiklamadiysak sifir return edicez demektir*/
                if (TempMovesList.size() == 0) {
                    System.out.print("debug1\n");
                    return 0;
                }
            /* ... ve daha once oynatabilecegimiz bir tasa tikladiysak ...*/
                else {
                /* ... tiklanan yer TempMoveList'te yani oynanabilir hamlelerde varmi diye bakiyoruz */
                /* eger var ise hamlemizi yapiyoruz ve listemizi temizleyip icine hamle source ve targetini atiyoruz*/
                    if (playUser(TempMovesList, currentCell)) {
                        System.out.print("debug2\n");
                        makeMove(getTempCell(), currentCell);
                        TempMovesList.clear();
                        setTempCell(emptyCell);
                    /* suanki oyuncu degerini degistiriyoruz */
                        setCurrentPlayer(!getCurrentPlayer());

                    /*PAWN SON SATIRA ULASTI MI DIYE BAKIYORUZ, ULASTIYSA PAWN KALE OLUR!!*/
                        checkPawn();

                    /* ve 2 komutunu return ederek hamle yaptik diyoruz */
                        return 2;
                    }
                /* eger tiklanan yer listede yoksa TempMovesListesini temizleyip sifir return ediyoruz */
                    else {
                        System.out.print("debug3\n");
                        TempMovesList.clear();
                        setTempCell(emptyCell);
                        return 0;
                    }
                }
            } else {
            /* Verilen cellde bi tas varsa TempMoveList'imize tasin oynayabilecegi yerlerin listesi aticaz ve 1
            return edicez ve kullanicinin targeti secmesini beklemek uzere beklemeye gecicez */
                System.out.print("debug4\n");
                tempCell.setCell(currentCell);
                TempMovesList.addAll(board.get(x).get(y).getPiece().checkMove(board, x, y));
                return 1;

            }
        } catch (NullPointerException e){
            return -1;
        }
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
            int lengt = cellList.size();
            for (int i = 0; i < lengt; ++i) {
                if(cellList.get(i).equals(cell))
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
        //System.out.print("Movemakera girdi\n");
        board.get(target.getX()).get(target.getY()).setPiece(source.getPiece());

        Pieces piece = new NoPiece();
        board.get(source.getX()).get(source.getY()).setPiece(piece);

        //yapilan hamle arraye kaydedildi, kaynak cell ve source cell olarak!
        removesss.push(new Cell(source));
        removesss.push(new Cell(target));

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
    public void playComputerEasy() {
        System.out.println(getCurrentPlayer());
        List<Cell> canMove = new ArrayList<>();
        List<Cell> trgtMove = new ArrayList<>();
        List<Cell> srcMove = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!(board.get(i).get(j).getPiece() instanceof NoPiece) &&
                        board.get(i).get(j).getPiece().getColor() == this.getCurrentPlayer()){
                    //System.out.println("im in"+i + " "+ j);
                    canMove.addAll(board.get(i).get(j).getPiece().checkMove(board, i, j));
                    //System.out.println("CAN MOVE:\n"+canMove.toString());
                    //System.out.println("player" + this.getCurrentPlayer());
                    for (int k = 0; k < canMove.size(); k++) {
                        srcMove.add(new Cell(board.get(i).get(j)));
                    }
                    trgtMove.addAll(canMove);
                    //System.out.println("SRC MOVE: \n"+srcMove.toString());

                    //System.out.println("TARGET MOVE:\n"+trgtMove.toString());

                    canMove.clear();
                }
            }
        }
        for(int i=0 ; i<trgtMove.size() ; ++i) {
            int x = trgtMove.get(i).getX(), y = trgtMove.get(i).getY();
            if (board.get(x).get(y).getPiece().getColor() == !this.getCurrentPlayer() &&
                    !(board.get(x).get(y).getPiece() instanceof NoPiece)) {
                System.out.println("-------->" + board.get(x).get(y).getPiece().getColor());
                System.out.println("-------->" + !this.getCurrentPlayer());

                makeMove(srcMove.get(i), trgtMove.get(i));
                this.setCurrentPlayer(!this.getCurrentPlayer());

                System.out.println("-------->" + x + " " + y);

                flag = false;
                break;
            }
        }
        if(flag) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(trgtMove.size());
            System.out.println("random: " + randomInt);
            makeMove(srcMove.get(randomInt), trgtMove.get(randomInt));

            this.setCurrentPlayer(!this.getCurrentPlayer());

        }

    }

    /**
     * Computer icin , medium mod secildiginde bu fonksiyon cagrilacak
     * Method icinde currentPlayer degistirilir
     * @return source ve targetin sirali olarak bulundugu bir cell listesi
     */
    public void playComputerMedium(){
        System.out.println(getCurrentPlayer());
        List<Cell> canMove = new ArrayList<>();
        List<Cell> trgtMove = new ArrayList<>();
        List<Cell> srcMove = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!(board.get(i).get(j).getPiece() instanceof NoPiece) &&
                        board.get(i).get(j).getPiece().getColor() == this.getCurrentPlayer()){
                    //System.out.println("im in"+i + " "+ j);
                    canMove.addAll(board.get(i).get(j).getPiece().checkMove(board, i, j));
                    //System.out.println("CAN MOVE:\n"+canMove.toString());
                    //System.out.println("player" + this.getCurrentPlayer());
                    for (int k = 0; k < canMove.size(); k++) {
                        srcMove.add(new Cell(board.get(i).get(j)));
                    }
                    trgtMove.addAll(canMove);
                    //System.out.println("SRC MOVE: \n"+srcMove.toString());

                    //System.out.println("TARGET MOVE:\n"+trgtMove.toString());

                    canMove.clear();
                }
            }
        }
        for(int i=0 ; i<trgtMove.size() ; ++i) {
            int x = trgtMove.get(i).getX(), y = trgtMove.get(i).getY();
            if (board.get(x).get(y).getPiece().getColor() == !this.getCurrentPlayer() &&
                    !(board.get(x).get(y).getPiece() instanceof NoPiece)) {
                System.out.println("-------->" + board.get(x).get(y).getPiece().getColor());
                System.out.println("-------->" + !this.getCurrentPlayer());

                makeMove(srcMove.get(i), trgtMove.get(i));
                this.setCurrentPlayer(!this.getCurrentPlayer());

                System.out.println("-------->" + x + " " + y);

                flag = false;
                break;
            }
        }
        if(flag) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(trgtMove.size());
            System.out.println("random: " + randomInt);
            makeMove(srcMove.get(randomInt), trgtMove.get(randomInt));
            this.setCurrentPlayer(!this.getCurrentPlayer());
        }
    }

    /**
     * Computer icin , hard mod secildiginde bu fonksiyon cagrilacak
     * Method icinde currentPlayer degistirilir
     * @return source ve targetin sirali olarak bulundugu bir cell listesi
     */
    public void playComputerHard(){
        fakeBoard = new ArrayList<ArrayList<Cell>>(getBoard());
        int depth = 5;
        maxofHard(depth);
    }
    private double maxofHard(int depth){
        double v = Double.NEGATIVE_INFINITY;
        List<Cell> notMove = new LinkedList<>();

        if(depth<=0){
            return evaluate();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fakeBoard.get(i).get(j).getPiece().getColor() == this.getCurrentPlayer() &&
                        !(fakeBoard.get(i).get(j).getPiece() instanceof NoPiece)){
                    notMove.addAll(fakeBoard.get(i).get(j).getPiece().checkMove(fakeBoard, i, j));

                    if(!notMove.isEmpty()){
                        for (int k = 0; k < notMove.size(); k++) {
                            makeMove(fakeBoard.get(i).get(j), notMove.get(k));
                            this.setCurrentPlayer(!this.getCurrentPlayer());
                            System.out.println("fake1 " + depth);
                            printFake();
                            double curVal = minofHard(--depth);
                            fakeBoard.get(fakeBoard.get(i).get(j).getX()).get(fakeBoard.get(i).get(j).getY()).setPiece(notMove.get(k).getPiece());
                            fakeBoard.get(notMove.get(k).getX()).get(notMove.get(k).getY()).setPiece(new NoPiece());
                            System.out.println("fake2 " + depth);
                            printFake();
                            if(curVal>v){
                                v=curVal;
                            }

                        }
                    }
                    notMove.clear();
                }
            }
        }
        return v;
    }
    /**
     * Computer icin minimax algoritmasindaki min methodu
     * bu method user icin en mantikli olmayan hamleyi oynayacak
     * @return source ve targetin icinde bulundugu bir liste
     */
    private double minofHard(int depth){
        double v = Double.POSITIVE_INFINITY;
        List<Cell> notMove = new LinkedList<>();
        if(depth<=0){
            return evaluate();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fakeBoard.get(i).get(j).getPiece().getColor() == !this.getCurrentPlayer() &&
                        !(fakeBoard.get(i).get(j).getPiece() instanceof NoPiece)){
                    notMove.addAll(fakeBoard.get(i).get(j).getPiece().checkMove(fakeBoard, i, j));

                    if(!notMove.isEmpty()){
                        for (int k = 0; k < notMove.size(); k++) {

                            makeMove(fakeBoard.get(i).get(j), notMove.get(k));
                            this.setCurrentPlayer(!this.getCurrentPlayer());

                            double curVal = maxofHard(--depth);
                            fakeBoard.get(fakeBoard.get(i).get(j).getX()).get(fakeBoard.get(i).get(j).getY()).setPiece(notMove.get(k).getPiece());
                            fakeBoard.get(notMove.get(k).getX()).get(notMove.get(k).getY()).setPiece(new NoPiece());
                            if(curVal<v){
                                v=curVal;
                            }
                        }
                    }
                    notMove.clear();
                }
            }
        }
        return v;
    }
    private double evaluate(){
        double total=0.0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!(fakeBoard.get(i).get(j).getPiece() instanceof NoPiece)) {
                    if (fakeBoard.get(i).get(j).getPiece().getColor() == this.getCurrentPlayer()) {
                        System.out.println("i- "+i+"j- "+j);

                        if(fakeBoard.get(i).get(j).getPiece() instanceof Pawn)
                            total += PAWN;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Knight)
                            total += KNIGHT;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Bishop)
                            total += BISHOP;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Rook)
                            total += ROOK;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Queen)
                            total += QUEEN;
/*                        else if(fakeBoard.get(i).get(j).getPiece() instanceof King)
                            total += KING;
*/
                    }
                    else{
                        if(fakeBoard.get(i).get(j).getPiece() instanceof Pawn)
                            total -= PAWN;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Knight)
                            total -= KNIGHT;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Bishop)
                            total -= BISHOP;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Rook)
                            total -= ROOK;
                        else if(fakeBoard.get(i).get(j).getPiece() instanceof Queen)
                            total -= QUEEN;
/*                        else if(fakeBoard.get(i).get(j).getPiece() instanceof King)
                            total -= KING;
*/
                    }

                }
            }
        }
        System.out.println("total-------- "+total);
        return total;
    }
    /**
     * kapatma dugmesine basildiginda oyunu kaydetmek icin yazilan metot
     * Yaptigi islem, Game class i attributelerinin degerlerini dosyaya yazmak
     */
    public void saveGame(){
        //Belirlenen bir dosyaya sifreli sekilde tahtanın son durumu kaydedilir

        try {
            //dosya classlar ile aynı director icinde olusturuluyor
            File file = new File("game.txt");

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
            for(int i=0; i<removesss.size()*2; i++) {
                bw.write(removesss.get(i).toString() + " ");
                bw.write("\n");
            }
            bw.write("\n");

            // son olarak da counterRemovess dosyaya yazilir
            bw.write(counterRemovess);

            // dosya kapatilir
            bw.close();

            String key = "I hope this project will finish8";

            File inputFile = new File("/Users/Desktop/Chess-Game-GTU/Chess-Game-GTU/src/sample/saveGame.txt");
            File encryptedFile = new File("/Users/Desktop/Chess-Game-GTU/Chess-Game-GTU/src/sample/saveGame.encrypted");

            try {
                CryptoUtils.encrypt(key, inputFile, encryptedFile);
            } catch (CryptoException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load dugmesine basildiginda oyunu yuklemek icin yazilan metot
     * Yaptigi islem, dosyadaki verileri Game classinin attributelerine atamak.
     */
    public void loadGame(){

        try {
            //dosya classlar ile aynı director icinde.
            File file = new File("game.txt");

            if (file.exists()) {
                FileReader fr = new FileReader(file.getAbsoluteFile());
                BufferedReader br = new BufferedReader(fr);
                char [] ch = new char[1];

                //READ
                readToFile(br, ch, 8, 8);
                // board dan sonra 3 tane \n sonra removess
                readToFile(br, ch, removesss.size(), 2);
                // son olarak da counterRemovess dosyadan okunur
                br.read(ch);
                counterRemovess = (int) ch[0];

                // dosya kapatilir
                br.close();

                String key = "I hope this project will finish.";

                File inputFile = new File("/Users/Desktop/Chess-Game-GTU/Chess-Game-GTU/src/sample/saveGame.txt");
                File encryptedFile = new File("/Users/Desktop/Chess-Game-GTU/Chess-Game-GTU/src/sample/saveGame.encrypted");

                try {
                    CryptoUtils.decrypt(key, inputFile, encryptedFile);
                } catch (CryptoException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bu method ayni satirlarin birden cok yerde kullanilmasi sebebiyle yazildi.
     * @param br, BufferedReader, bu parametre ile dosyadan okuma islemi gerceklestirildi.
     * @param ch, okunan degerler bu parametreye kaydedilip sonra islendi
     * @param row, ilk dongu icin UST SINIR
     * @param col, ikinci dongu icin UST SINIR
     */
    void readToFile(BufferedReader br, char[] ch, int row, int col){
        try {
            int i, j;
            //tahtanin son hali dosyadan okundu
            // hucre\nhucre\n......\nhucre\n\n -> 1 row bu sekilde yaziliyor sonra iki \n sonra diger row!
            for (i = 0; i < 8; i++) {
                for (j = 0; j < 8; j++) {
                    br.read(ch);
                    board.get(i).get(j).setX((int) ch[0]);
                    br.read(ch); // " " ifadesi icin
                    br.read(ch);
                    board.get(i).get(j).setY((int) ch[0]);
                    br.read(ch); // " " ifadesi icin
                    br.read(ch);
                    Pieces p = new NoPiece();
                    if (ch.equals("Pawn"))
                        p = new Pawn();
                    else if (ch.equals("Bishop"))
                        p = new Bishop();
                    else if (ch.equals("King"))
                        p = new King();
                    else if (ch.equals("Knight"))
                        p = new Knight();
                    else if (ch.equals("Queen"))
                        p = new Queen();
                    else if (ch.equals("Rook"))
                        p = new Rook();
                    br.read(ch);// " " ifadesi icin
                    br.read(ch);
                    if (ch.equals("true"))
                        p.setColor(true);
                    else
                        p.setColor(false);
                    board.get(i).get(j).setPiece(p);
                }
                br.read(ch);
            }
            br.read(ch);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * oyuna tekrar baslanmak istendiginde yazilacak kod
     */
    public void restartGame(){
        //tahta ilk haline getirildi
        this.initBoard();

        //hamlelerin tutuldugu array silindi, tekrar oluşturuldu.
        removesss = null;
        removesss = new Stack<Cell>();
        for(int i =0; i < 64*2; ++i) {
            removesss.add(new Cell());
        }

        //geri alinan hamle sayisi da sifirlandi
        counterRemovess=0;
    }

    /**
     * Geri alma islemine basildiginda en son yapilan hamleyi geri alacak
     * removesss in 2. sutunundaki cell den 1. sutunundaki Cell hareket edilir
     * ve bu hamle artık yapilmadi varsayilip removesss dan kaldirilir.
     */
    public void recallMove(){
        //removesss.size 0 dan buyuk olmalı cunku en az bir hamle yapılmadan geri alma işemi gerçekleştirilemez
        //yani oyunun başında bu buton calismaz!!!
        if(counterRemovess <= 5 && removesss.size() > 0){
            Cell sourceCell = new Cell();
            Cell targetCell = new Cell();

            //stackten son hamle silindi!
            sourceCell.setCell(removesss.pop());
            targetCell.setCell(removesss.pop());

            //index son eleman, index-1 bir önceki; son eleman target(new source), önceki eleman source(new target)!
            //geri almak icin target source'a tasinir
            board.get(targetCell.getX()).get(targetCell.getY()).setCell(board.get(sourceCell.getX()).get(sourceCell.getY()));
            board.get(targetCell.getX()).get(targetCell.getY()).setPiece(board.get(sourceCell.getX()).get(sourceCell.getY()).getPiece());

            counterRemovess++;
        }
        else
            System.out.println("INVALID!! You can not go back anymore!!");
    }

    /**
     * piyon tahtanın sonuna geldiginde degisir!
     */
    public void checkPawn(){
        //beyaz oynuyor ise en UST SATIR kontol edilir. ROW=0
        //siyah oynuyor ise en ALT SATIR kontrol edilir. ROW=7
        int row = 7;
        if(getCurrentPlayer())//true, beyaz
            row = 0;


        for(int j=0; j<8; j++)
            if(board.get(row).get(j).getPiece() instanceof Pawn && board.get(row).get(j).getPiece().getColor() == getCurrentPlayer()){
                Pieces piece = new Rook();
                piece.setColor(getCurrentPlayer()); //piece rengi oynayan oyuncunun rengi olur.
                board.get(row).get(j).setCell(new Cell(row, j, piece));
            }
    }

    public void initBoard(){
        int i; int j;

        Game.currentPlayer = true;

        //Bos hucreler
        for(i=0; i < 8; ++i){
            for(j=0; j < 8; ++j){
                try
                {
                    //boş hücreler
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
    public void printFake(){
        for(int j=7; j>=0; j--) {
            for (int i = 0; i <= 7; i++) {
                if (!fakeBoard.get(i).get(j).piece.getColor()) {
                    //System.out.println("PrintBoard, false, siyah!!");
                    if (fakeBoard.get(i).get(j).getPiece() instanceof Pawn) {
                        System.out.print(" P");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Rook) {
                        System.out.print(" K");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Knight) {
                        System.out.print(" A");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Bishop) {
                        System.out.print(" F");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof King) {
                        System.out.print(" S");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Queen) {
                        System.out.print(" V");
                    } else
                        System.out.print(" .");
                } else if (fakeBoard.get(i).get(j).piece.getColor()) {
                    //System.out.println("PrintBoard, true, beyaz!!");
                    if (fakeBoard.get(i).get(j).getPiece() instanceof Pawn) {
                        System.out.print("-P");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Rook) {
                        System.out.print("-K");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Knight) {
                        System.out.print("-A");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Bishop) {
                        System.out.print("-F");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof King) {
                        System.out.print("-S");
                    } else if (fakeBoard.get(i).get(j).getPiece() instanceof Queen) {
                        System.out.print("-V");
                    } else
                        System.out.print(" .");
                }
            }
            System.out.println();
        }
    }

    public void printBoard(){
        for(int j=7; j>=0; j--) {
            for (int i = 0; i <= 7; i++) {
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

    public boolean isEnd(){
        int counter = 0;
        for(int i=0; i <= 7; ++i){
            for(int j=0; j <= 7; ++j){
                if(this.board.get(i).get(j).getPiece() instanceof King){
                    if(counter == 0){
                        ++counter;
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int kingPosition(boolean kingColor){
        for(int i=0; i <= 7; ++i){
            for(int j=0; j <= 7; ++j){
                if(board.get(i).get(j).getPiece() instanceof King && board.get(i).get(j).getPiece().getColor() == kingColor){
                    return (10*i+j);
                }
            }
        }

        System.out.print("KingPosition hatası\n");
        return -1;
    }

    public static void setCurrentPlayer( boolean cPlayer ) { currentPlayer = cPlayer; }
    public static boolean getCurrentPlayer() { return currentPlayer; }

    public static void setIsComputerOn( int isOn ) { isComputerOn = isOn; }
    public static int getIsComputerOn() { return isComputerOn; }

    public void setTempCell( Cell target ) { tempCell.setCell(target); }
    public Cell getTempCell() { return tempCell; }
}