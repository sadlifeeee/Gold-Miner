package Scripts;


public class Parser {
    public Block[][] convertToGrid(String[] rows, int dimensions) {
        Block[][] temp = new Block[dimensions][dimensions];

        for (int i = 0; i < dimensions; i++)
            for (int j = 0; j < dimensions; j++)

                switch (rows[i].charAt(j)) {
                    case '-' -> temp[i][j] = new Block(i + 1, j + 1);
                    case 'P' -> temp[i][j] = new Pit(i + 1, j + 1);
                    case 'B' -> temp[i][j] = new Beacon(i + 1, j + 1);
                    case 'G' -> temp[i][j] = new Gold(i + 1, j + 1);
                }

        return temp;
    }

}