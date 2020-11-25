package Scripts;

public class Miner {
    // Initialized at Upper-left corner
    public Miner() {
        this.X = 0;
        this.Y = 0;
        this.scans = 0;
        this.moves = 0;
        this.rotations = 0;

        // Default Direction = "Right"
        this.direction = "right";
    }

    public void rotate() {
        if (direction.equalsIgnoreCase("up")) {
            direction = "right";
        }
        else if (direction.equalsIgnoreCase("down")) {
            direction = "left";
        }
        else if (direction.equalsIgnoreCase("left")) {
            direction = "up";
        }
        else if (direction.equalsIgnoreCase("right")) {
            direction = "down";
        }
        rotations++;
    }

    public void moveForwardOneBlock() {
        switch (direction) {
            case "right" -> X++;
            case "left" -> X--;
            case "up" -> Y--;
            case "down" -> Y++;
        }
        moves++;
    }

    public void printGameConclusion(Block[][] miningGrid) {
        if (miningGrid[Y][X] instanceof Gold)
            System.out.print("=== GOLD FOUND! ===");
        else if (miningGrid[Y][X] instanceof Pit)
            System.out.print("=== FELL INTO PIT! GAME OVER ===");
        else
            System.out.print("Exception Handled!");
    }

    public void incrementScans() {
        scans++;
    }

    public String getDirection() {
        return direction;
    }

    public int getScans() {
        return scans;
    }

    public int getMoves() {
        return moves;
    }

    public int getRotations() {
        return rotations;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    private String direction;
    private int X;
    private int Y;
    private int scans;
    private int moves;
    private int rotations;
}