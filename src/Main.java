import Scripts.*;
import com.sun.source.tree.BreakTree;

import java.util.*;

//  TODO Beacon's functionality - I think BFS

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
                System.out.print("Perform Intelligent Search ? [Y/N] : ");
                String temp = kb.next();
                if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("Yes"))
                    isIntelligent = 1;
                else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("No"))
                    isIntelligent = 0;
            } while (isIntelligent == -1);

            do {
                System.out.print("Perform in simultaneous succession ? [Y/N] : ");
                String temp = kb.next();
                if (temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("Yes"))
                    isSimultaneousPreview = 1;
                else if (temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("No"))
                    isSimultaneousPreview = 0;
            } while (isSimultaneousPreview == -1);

            printCurrentGameState(new Miner(), miningGrid, dimension, null);

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

    // Current rationale: Graph-DFS BUT break when see beacon - then turn to A*
    // in Graph-DFS, make sure to scan the surroundings and have a priority for specific blocks gold > beacon > none > pit/edge
    private static void performIntelligentSearch(Miner miner, int isSimultaneousPreview, Block[][] miningGrid, int dimension) {
        ArrayList<Block> visitedNodes = new ArrayList<Block>();
        Stack nodePath = new Stack();
        Block scannedBlockInFront;
        //boolean hasDiscoveredABeacon = false; // TODO this boolean state only is used once!!!
        //boolean hasMadeAFullSpin = false; // TODO this boolean state only is used once!!!

        while (canMinerStillPlay(miner, miningGrid)) {
            scannedBlockInFront = scanFront(miner, miningGrid);

            // TODO be careful the miner scans their nodePath OR just prevent them from doing so
            if (scannedBlockInFront == null ||
                    //visitedNodes.contains(scannedBlockInFront) ||
                    scannedBlockInFront instanceof Pit) {
                miner.rotate();
            } else {
                miner.moveForwardOneBlock();

                //  TODO debug AIOOBE issue
//                visitedNodes.add(miningGrid[miner.getY()][miner.getX()]);
//                nodePath.push(miningGrid[miner.getY()][miner.getX()]);
            }

            //  TODO Configure the step-by-step preview OR rapid preview
            printCurrentGameState(miner, miningGrid, dimension, scannedBlockInFront);
        }

        miner.printGameConclusion(miningGrid);
    }


    private static void printCurrentGameState(Miner miner, Block[][] miningGrid, int dimension, Block scannedBlockInFront) {
        System.out.println("\n\nMining Grid : \n");

        for (int y = 0; y < dimension; y++)
            for (int x = 0; x < dimension; x++) {
                if (x == 0)
                    System.out.print("\t");

                //  The icon of the Miner has higher precedence than the tiles of the mining grid
                if (x == miner.getX() && y == miner.getY())
                    System.out.print("M");
                else
                    System.out.print(miningGrid[y][x].getIcon());

                if (x == dimension - 1)
                    System.out.println();
            }

        System.out.println("\n=============================================");
        System.out.println("Position \t\t\t: ( " + (miner.getX() + 1) + " , " + (miner.getY() + 1) + " )");
        System.out.println("Facing Direction \t: " + miner.getDirection());
        System.out.print("Total Scans \t\t: " + miner.getScans() + "\t\t[Latest Scan: ");
        if (scannedBlockInFront == null)
            System.out.print("Edge");
        else if (scannedBlockInFront instanceof Pit)
            System.out.print("Pit");
        else if (scannedBlockInFront instanceof Beacon)
            System.out.print("Beacon");
        else if (scannedBlockInFront instanceof Gold)
            System.out.print("Gold");
        else
            System.out.print("None");
        System.out.println("]");
        System.out.println("Total Rotations \t: " + miner.getRotations());
        System.out.println("Total Moves \t\t: " + miner.getMoves());
    }

    private static Block scanFront(Miner miner, Block[][] miningGrid) {
        Block toReturn = null;
        try {
            switch (miner.getDirection()) {
                case "right" -> {
                    toReturn = miningGrid[miner.getY()][miner.getX() + 1];
                }
                case "left" -> {
                    toReturn = miningGrid[miner.getY()][miner.getX() - 1];
                }
                case "up" -> {
                    toReturn = miningGrid[miner.getY() - 1][miner.getX()];
                }
                case "down" -> {
                    toReturn = miningGrid[miner.getY() + 1][miner.getX()];
                }
            }
        } catch (Exception e) {
            toReturn = null;
        }
        miner.incrementScans();
        return toReturn;
    }

    private static boolean canMinerStillPlay(Miner miner, Block[][] miningGrid) {
        return !((miningGrid[miner.getY()][miner.getX()] instanceof Pit) ||
                (miningGrid[miner.getY()][miner.getX()] instanceof Gold));
    }
}