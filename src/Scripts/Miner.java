package Scripts;

public class Miner {
  /*
    Suggestion lang naman I am not sure how to make this smart but 
    maybe we can count the moves? 
    I am not sure pa - Jerickson 
    we can either count the miner moves by having the miner has an attribute for its moves or the program increments a local counter var

  */

    // Initialized at Upper-left corner
    public Miner() {
        this.X = 1;
        this.Y = 1;

        // Default Direction = "Right"
        direction = "right";
    }

    // Will Rotate 90 Degrees
    public void rotate() {
        if (direction.equalsIgnoreCase("up"))
            direction = "right";
        else if (direction.equalsIgnoreCase("down"))
            direction = "left";
        else if (direction.equalsIgnoreCase("left"))
            direction = "up";
        else if (direction.equalsIgnoreCase("right"))
            direction = "down";
    }

  /*
    Direction of the Front

    "up" = up
    "down" = down
    "left" = left
    "right" = right
  */
    private String direction;
    private int X;
    private int Y;
}