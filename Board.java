/**
 * Represents the Board for Kolor Lines games
 *
 * The Board is composed of squares and has a defined size
 */
public class Board {

    public Board(int size){
        Position.setMaxWidth(size);
        Position.setMaxLength(size);

        this.size = size;
        this.squares = new Square[size][size];
    }

    public Square getSquare(Position pos){
        return this.squares[pos.getAbs()][pos.getOrd()];
    }

    public void setSquare(Position pos){
        this.squares[pos.getAbs()][pos.getOrd()] = new Square(pos);
    }

    public void display(){
        String formattedTable = "";

        formattedTable += String.format("%10s", "/");
        for (int colIndex=0; colIndex < this.squares[0].length; colIndex++){
            formattedTable += String.format("%10s", colIndex);
        }
        formattedTable += "\n";


        for (int rowIndex=0; rowIndex < this.squares.length; rowIndex++){
            for (int colIndex=0; colIndex < this.squares[rowIndex].length; colIndex++){
                if (colIndex == 0){
                    formattedTable += String.format("%10s", colIndex);
                }
                if (Square.class.isInstance(this.squares[rowIndex][colIndex])){
                    formattedTable += String.format("%10s", this.squares[rowIndex][colIndex].getColor());
                }
                else {
                    formattedTable += String.format("%10s", " ");
                }

            }
            formattedTable += "\n";
        }

        System.out.print(formattedTable);
    }

    private int size;
    private Square[][] squares;
}
