// Operations for Catan
//
// CS201 final project - Catan

public class CatanOps {

    //takes in a Hex, outputs a list of adjacent hexes
    public static Hex[] adjacentHexes(Hex h) {
        int row = h.getRow();
        int col = h.getCol();
        Hex[] result = new Hex[6];
        if (row % 2 == 0) {
            result.add(BoardCanvas.hexes[row-1][col-1]);
            result.add(BoardCanvas.hexes[row-1][col]);
            result.add(BoardCanvas.hexes[row][col+1]);
            result.add(BoardCanvas.hexes[row+1][col]);
            result.add(BoardCanvas.hexes[row+1][col-1]);
            result.add(BoardCanvas.hexes[row][col-1]);
        }
        else {
            result.add(BoardCanvas.hexes[row-1][col]);
            result.add(BoardCanvas.hexes[row-1][col+1]);
            result.add(BoardCanvas.hexes[row][col+1]);
            result.add(BoardCanvas.hexes[row+1][col+1]);
            result.add(BoardCanvas.hexes[row+1][col]);
            result.add(BoardCanvas.hexes[row][col-1]);
        }
        return result;
    }

    public static void main(String[] args) {
        return;
    }

}
