import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Değerlendirme formu 5: MineSweeper sınıfı içerisine metodlar oluşturuldu.
    int row;
    int column;
    int size;
    String[][] mineField;
    String[][] userField;
    int counter = 0;

    public MineSweeper(int row, int column) // Değerlendirme formu 7: Matris boyutları parametre olarak kullanıcı tarafından belirlenir.
    {
        this.row = row;
        this.column = column;
        this.mineField = new String[row][column];
        this.userField = new String[row][column];
        this.size = row * column;
    }

    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    public void administrator(String[][] field) // String tipinde 2 boyutlu bir array parametresi ile userField(Player Map veya mineField(Admin Map) oluşturur.
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if ("*".equals(field[i][j])) {
                    System.out.print(field[i][j] + " ");
                } else {
                    field[i][j] = "-";
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void playerMap(int rowInput, int colInput) {
        for (int i = 0; i < userField.length; i++) {
            for (int j = 0; j < userField[i].length; j++) {
                if (userField[i][j].equals(userField[rowInput][colInput])) {
                    userField[rowInput][colInput] = String.valueOf(counter);
                    System.out.print(userField[rowInput][colInput] + " ");
                    continue;
                }
                System.out.print(userField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mineAdder() // Değerlendirme formu 8: Diziye rastgele mayın yerleştirici.
    {
        int addToRow;
        int addToColumn;
        int count = 0;
        while (count != (size / 4)) {
            addToRow = rand.nextInt(row);
            addToColumn = rand.nextInt(column);
            if (!"*".equals(mineField[addToRow][addToColumn])) {
                mineField[addToRow][addToColumn] = "*";

                count++;
            }
        }
    }

    public void gamePlay() // Değerlendirme formu 6: Kazanma, kaybetme gibi dinamikler tanımlandı.
    {
        int rowInput, colInput, winCounter = 0;
        mineAdder();
        administrator(mineField);
        System.out.println("====================");
        administrator(userField);
        while (true) {
            System.out.println("Row: "); // Değerlendirme formu 9: Row(satır) ve Column(Sütun) bilgisi kullanıcıdan alınır.
            rowInput = input.nextInt();
            if (rowInput < 0 || rowInput > row - 1) {
                System.out.println("Error: Out of bounds."); // Değerlendirme formu 10: Koordinat, matriks sınırları içerisinde değilse uyarı verir.
                continue;
            }
            System.out.println("Column: ");
            colInput = input.nextInt();
            if (colInput < 0 || colInput > column - 1) {
                System.out.println("Error. Out of bounds.");
                continue;
            }
            if (!userField[rowInput][colInput].equals("-")) {
                System.out.println("This coordinates have been already tried before. ");
                continue;
            }

            gameMechanics(rowInput, colInput); // Kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya gösterilir.

            if ((mineField[rowInput][colInput].equals("*"))) // Değerlendirme formu 11-13: Mayın seçilirse oyun bitirilir. Seçilmezse oyun alanı güncellenir ve oyun devam eder.
            {
                System.out.println("BOOM. Game Over.");
                System.out.println("You have lost.");
                break;
            } else {
                playerMap(rowInput, colInput); // Değerlendirme formu 14: Tüm noktalar doğru bir şekilde temizlendiyse oyunu kazanma kontrolü yapılır.
                this.counter = 0;
                System.out.println("Good choice");
                winCounter++;
                bracketEquals();
                if (winCounter == size - (size * 25 / 100)) {
                    System.out.println("Congratulations. Perfect!");
                    System.out.println("You have won!");
                    input.close();
                    break;
                }
            }
        }
    }

    // Oyun mekanikleri; Kullanıcının girdiği noktanın sağ sol ön arka ve üst alt çapraz noktalarını kontrol eder.
    // Var ise, counter sayacı arttırılır ve sayacın son geldiği rakam string.valueOf() tekniği ile yazırılır.
    public void gameMechanics(int rowInput, int colInput) {
        if (mineField[rowInput][colInput].equals("-")) {
            if ((colInput < column - 1) && mineField[rowInput][colInput + 1].equals("*")) {
                counter++;
            }
            if ((colInput > 0) && mineField[rowInput][colInput - 1].equals("*")) {
                counter++;
            }
            if ((rowInput > 0) && mineField[rowInput - 1][colInput].equals("*")) {
                counter++;
            }
            if ((rowInput < row - 1) && mineField[rowInput + 1][colInput].equals("*")) {
                counter++;
            }
            if (((rowInput > 0) && (colInput < column - 1) && mineField[rowInput - 1][colInput + 1].equals("*"))) {
                counter++;
            }
            if ((rowInput > 0) && (0 < colInput) && mineField[rowInput - 1][colInput - 1].equals("*")) {
                counter++;
            }
            if (((rowInput < (row - 1)) && (colInput > 0) && mineField[rowInput + 1][colInput - 1].equals("*"))) {
                counter++;
            }
            if ((rowInput < (row - 1)) && (colInput < (column - 1) && mineField[rowInput + 1][colInput + 1].equals("*"))) {
                counter++;
            }

            userField[rowInput][colInput] = String.valueOf(counter);
        }
    }

    public void bracketEquals(){
        System.out.println("============================");
    }



}