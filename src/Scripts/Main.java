package Scripts;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner kb = new Scanner(System.in);
        Block[][] miningGrid;
        int dimension;

        System.out.print("Size of Rectangular Grid : ");

        try {
            dimension = kb.nextInt();
            while (dimension < 8 && dimension > 64) {
                System.out.println("Invalid Input");
                System.out.print("Size of Rectangular Grid: ");

                dimension = kb.nextInt();
                String[] rowInputs = new String[dimension];
                miningGrid = new Block[dimension][dimension];

                //  Collectively acquire all the rows from Console before Parsing
                for (int i = 0; i < dimension; i++)
                    rowInputs[i] = kb.nextLine();

                //  Parse and acquire instantiated Mining Grid
                //miningGrid = parser.convertToGrid(rowInputs);
            }


        } catch (InputMismatchException e) {
            System.out.println("ERROR || Non-Integer entered!");
        }

        kb.close();
    }
}