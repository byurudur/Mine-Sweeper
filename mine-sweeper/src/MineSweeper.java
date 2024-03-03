import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int column;
    int size;
    String[][] mineField;
    String[][] userField;
    int counter = 0;
    public MineSweeper(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.mineField = new String[row][column];
        this.userField = new String[row][column];
        this.size = row * column;
    }

    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    public void administrator(String[][] field)
    {
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
               if("*".equals(field[i][j]))
               {
                   System.out.print(field[i][j] + " ");
               }else
               {
                   field[i][j] = "-";
                   System.out.print(field[i][j] + " ");
               }
            }
            System.out.println();
        }
    }

    public void player(int rowInput, int colInput)
    {
        for (int i = 0; i < userField.length; i++) {
            for (int j = 0; j < userField[i].length; j++)
            {
                if (userField[i][j].equals(userField[rowInput][colInput]))
                {
                    userField[rowInput][colInput] = String.valueOf(counter);
                    System.out.print(userField[rowInput][colInput] + " ");
                    continue;
                }
                System.out.print(userField[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void mines() // This method randomly plants mines to rows and columns.
    {
        int row1;
        int column1;
        int count = 0;
        while (count != (size / 4)) {
            row1 = rand.nextInt(row);
            column1 = rand.nextInt(column);
            if (!"*".equals(mineField[row1][column1])) {
                mineField[row1][column1] = "*";

                count++;
            }
        }
    }

    public void game() // Game method involves mines, administrator field and user field.
    {
        int rowInput, colInput, counter =0 ;
        mines();
        administrator(mineField);
        System.out.println("====================");
        administrator(userField);
        while (true){
            System.out.println("Row: ");
            rowInput = input.nextInt();
                if (rowInput < 0 || rowInput > row - 1){
                    System.out.println("Error: Out of bounds.");
                    continue;
                }
            System.out.println("Column: ");
            colInput = input.nextInt();
                if (colInput < 0 || colInput > column - 1){
                    System.out.println("Error. Out of bounds.");
                    continue;
                }

            gamePlay(rowInput, colInput);
            player(rowInput, colInput);
            if ((mineField[rowInput][colInput].equals("*")))
            {
                System.out.println("BOOM");
                break;
            }
            else
            {
                System.out.println("Good choice");
                counter++;
                if (counter == size - (size * 25 / 100))
                {
                    System.out.println("Congratulations. Perfect!");
                    break;
                }
            }
        }
    }

    
    public void gamePlay(int rowInput, int colInput) {
        if (mineField[rowInput][colInput].equals("-")) {
            if ((colInput < column - 1) && mineField[rowInput][colInput + 1].equals("*"))
            {
                counter++;
            }
            if ((colInput > 0) && mineField[rowInput][colInput - 1].equals(" - "))
            {
                counter++;            }

            if ((rowInput > 0) && mineField[rowInput - 1][colInput].equals("*"))

                counter++;            }

            if ((rowInput < row - 1) && mineField[rowInput + 1][colInput].equals("*"))
            {
                counter++;            }

            if ((colInput > 0) && mineField[rowInput][colInput - 1].equals("*"))
            {
                counter++;            }

            if ((rowInput > 0) && mineField[rowInput - 1][colInput].equals("*"))
            {
                counter++;            }

            if (((rowInput > 0) && (colInput < column - 1) && mineField[rowInput - 1][colInput + 1].equals("*")))
            {
                counter++;            }

            if ((rowInput > 0) && (0 < colInput) && mineField[rowInput - 1][colInput - 1].equals("*"))
            {
                counter++;            }

            if (((rowInput < (row - 1)) && (colInput > 0) && mineField[rowInput + 1][colInput - 1].equals("*")))
            {
                counter++;            }

            if ((rowInput < (row - 1)) && (colInput < (column - 1) && mineField[rowInput + 1][colInput + 1].equals("*"))) {

                counter++;

            }

        userField[rowInput][colInput] = String.valueOf(counter);
    }
}

