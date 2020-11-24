import Scripts.*;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner kb = new Scanner(System.in);
        Block[][] miningGrid;
        int isIntelligent = -1;
        int isSimultaneousPreview = -1;
        int dimension;

        try {
            do {
                System.out.print("Size of Rectangular Grid: ");

                dimension = kb.nextInt();
            } while (dimension <= 0);
            //} while (dimension < 8 || dimension > 64);
            //  TODO : Remember to uncomment the restriction above

            String[] rowInputs = new String[dimension];
            miningGrid = new Block[dimension][dimension];

            //  Collectively acquire all the rows from Console before Parsing
            for (int i = 0; i < dimension; i++) {
                System.out.printf("[l %d] : ", i + 1);
                rowInputs[i] = kb.next();
            }

            //  Parse and acquire instantiated Mining Grid
            miningGrid = parser.convertToGrid(rowInputs, dimension);

            do {
                System.out.println("Perform Intelligent Search ? [Y/N] : ");
                String temp = kb.nextLine();
                if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("Yes"))
                    isIntelligent = 1;
                else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("No"))
                    isIntelligent = 0;
            } while (isIntelligent == -1);

            do {
                System.out.println("Perform in simultaneous succession ? [Y/N] : ");
                String temp = kb.nextLine();
                if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("Yes"))
                    isSimultaneousPreview = 1;
                else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("No"))
                    isSimultaneousPreview = 0;
            } while (isSimultaneousPreview == -1);

            if (isIntelligent == 1)
                performIntelligentSearch(new Miner(), isSimultaneousPreview, miningGrid, dimension);
            else
                performRandomSearch(new Miner(), isSimultaneousPreview, miningGrid, dimension);

        } catch (InputMismatchException e) {
            System.out.println("ERROR || Invalid Data type inserted!");
        }

        kb.close();
    }

    private static void performRandomSearch(Miner miner, int isSimultaneousPreview, Block[][] miningGrid, int dimension) {
        //  TODO : Perform this in a seperate branch
    }

    private static void performIntelligentSearch(Miner miner, int isSimultaneousPreview, Block[][] miningGrid, int dimension) {
        while (canMinerStillPlay(miner, miningGrid)) {
            printCurrentGameState(miner, miningGrid, dimension);
        }
    }

    private static void printCurrentGameState(Miner miner, Block[][] miningGrid, int dimension) {
        System.out.println("Mining Grid : \n");

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                //  The icon of the Miner has higher precedence than the tiles of the mining grid
                if (i + 1 == miner.getX() && j + 1 == miner.getY()) {
                    System.out.print("M");
                } else {
                    if (miningGrid[i][j] instanceof Pit)
                        System.out.print("P");
                    else if (miningGrid[i][j] instanceof Beacon)
                        System.out.print("B");
                    else if (miningGrid[i][j] instanceof Gold)
                        System.out.print("G");
                    else
                        System.out.print("-");
                }

                if (j == dimension - 1)
                    System.out.println();
            }

        System.out.println("\n\n=============================================");
        System.out.println("Facing Direction \t: " + miner.getDirection());
        System.out.println("Total Scans \t: " + miner.getScans());
        System.out.println("Total Moves \t: " + miner.getMoves());
    }

    private static boolean canMinerStillPlay(Miner miner, Block[][] miningGrid) {
        return !((miningGrid[miner.getX() - 1][miner.getY() - 1] instanceof Pit) ||
                (miningGrid[miner.getX() - 1][miner.getY() - 1] instanceof Gold));
    }
}