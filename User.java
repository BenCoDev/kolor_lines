abstract public class User {

//    public User() {
//    }

    // Play

    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    private int score = 0;
    private Board board; // aggregation
}
