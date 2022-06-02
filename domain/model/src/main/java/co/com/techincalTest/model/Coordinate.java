package co.com.techincalTest.model;

public class Coordinate {
    public char dna[][];
    public int subIndex;
    public int row;
    public int column;
    public char lastChar;
    public char curruntChar;
    public int safeIndex;
    public int size;

    public Coordinate(char[][] dna, int subIndex, int row, int column) {
        super();
        this.dna = dna;
        this.safeIndex = dna.length - 1;
        this.size = dna.length;
        this.subIndex = subIndex;
        this.row = row;
        this.column = column;
        this.lastChar = dna[row][column];
    }

    @Override
    public String toString() {
        return "Coordinate [row=" + row + ", column=" + column + ", lastChar=" + lastChar + ", curruntChar="
                + curruntChar + "]";
    }

    public static Coordinate at(char[][] dna, int row, int column) {
        return new Coordinate(dna, 0, row, column);
    }

    public static Coordinate at(char[][] dna, int row, int column, int index) {
        return new Coordinate(dna, index, row, column);
    }
}
