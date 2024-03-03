import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int column;
    int size;
    int[][] mineField;

    String eksi = " - ";
    String sifir = " 0 ";
    int[][] userField;

    public MineSweeper(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.mineField = new int[row][column];
        this.userField = new int[row][column];
        this.size = row * column;
    }

    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    public void administrator(int[][] field)
    {
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
                if (field[i][j] == -1)
                {
                    System.out.print(" * ");
                }
                else
                {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public void player(int[][] field)
    {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (userField[i][j] == 0){
                    System.out.print(eksi);
                }else {
                    System.out.print(" " + field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public void mines()
    {
        int row1;
        int column1;
        int count = 0;
        while (count != (size / 4)) {
            row1 = rand.nextInt(row);
            column1 = rand.nextInt(column);
            if (mineField[row1][column1] != 1) {
                mineField[row1][column1] = -1;

                count++;
            }
        }
    }

    public void game()
    {
        int rowInput, colInput, counter =0 ;
        mines();
        administrator(mineField);
        System.out.println("====================");
        while (true)
        {
            player(userField);
            System.out.println("Row: ");
            rowInput = input.nextInt();
            if ((rowInput < 0) || (rowInput > row))
            {
                System.out.println("Error");
                continue;
            }
            System.out.println("Col: ");
            colInput = input.nextInt();

            if ((colInput < 0) || (colInput > column))
            {
                System.out.println("Error");
                continue;
            }
            gamePlay(rowInput, colInput);

            for (int i = 0; i < userField.length; i++) {
                for (int j = 0; j < userField[i].length; j++) {
                    if ( userField[rowInput][colInput] == 0){
                        System.out.print(userField[i][j] + "");
                    }
                }
                System.out.println();
            }

            if ((mineField[rowInput][colInput] == -1))
            {
                System.out.println("BOOM");
                break;
            }
            else if ((mineField[rowInput][colInput] != -1))
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
        if (mineField[rowInput][colInput] == 0) {
            if ((colInput < column - 1) && mineField[rowInput][colInput + 1] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if ((colInput > 0) && mineField[rowInput][colInput - 1] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if ((rowInput > 0) && mineField[rowInput - 1][colInput] == -1)

                userField[rowInput][colInput]++;
            }
            if ((rowInput < row - 1) && mineField[rowInput + 1][colInput] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if ((colInput > 0) && mineField[rowInput][colInput - 1] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if ((rowInput > 0) && mineField[rowInput - 1][colInput] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if (((rowInput > 0) && (colInput < column - 1) && mineField[rowInput - 1][colInput + 1] == -1))
            {
                userField[rowInput][colInput]++;
            }
            if ((rowInput > 0) && (0 < colInput) && mineField[rowInput - 1][colInput - 1] == -1)
            {
                userField[rowInput][colInput]++;
            }
            if (((rowInput < (row - 1)) && (colInput > 0) && mineField[rowInput + 1][colInput - 1] == -1))
            {
                userField[rowInput][colInput]++;
            }
            if ((rowInput < (row - 1)) && (colInput < (column - 1) && mineField[rowInput + 1][colInput + 1] == -1)) {
                userField[rowInput][colInput]++;

            boolean isClear = true;

            while (isClear)
            {


            }
            }
        }
    }

