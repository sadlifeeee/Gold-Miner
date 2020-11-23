package Scripts;

public class Block {
    /*
      P - Pit
      - - Normal Block
      B - Beacon
      G - Gold
    */
    public Block(int x, int y, char icon) {
        this(x, y);
        this.icon = icon;
    }

    public Block(int x, int y) {
        this.X = x;
        this.Y = y;
        this.icon = '-';
    }

    public int X() {
        return X;
    }

    public int Y() {
        return Y;
    }

    public char getIcon() {
        return icon;
    }

    private int X;
    private int Y;
    private char icon;
}