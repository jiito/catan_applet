// Operations for Catan
//
// CS201 final project - Catan

public class CatanOps {

    //test 2D hex array:
    public static Hex[][] hexesTest = new Hex[5][5];

    public static void populateHexTest() {
        for (int i = 0; i <= 4; i++) {
            for (int x = 0; x <= 4; x++) {
                Hex hex = new Hex(0, 0, 0, x, i, 6);// change to be random
                hexesTest[x][i] = hex;
            }
        }
    }

    //takes in a Hex, outputs a list of adjacent hexes
    public static Hex[] adjacentHexes(Hex h) {
        int row = h.getRow();
        int col = h.getCol();
        Hex[] result = new Hex[6];
        if (row % 2 == 0) {
            result[0] = hexesTest[row-1][col-1];
            result[1] = hexesTest[row-1][col];
            result[2] = hexesTest[row][col+1];
            result[3] = hexesTest[row+1][col];
            result[4] = hexesTest[row+1][col-1];
            result[5] = hexesTest[row][col-1];
        }
        else {
            result[0] = hexesTest[row-1][col];
            result[1] = hexesTest[row-1][col+1];
            result[2] = hexesTest[row][col+1];
            result[3] = hexesTest[row+1][col+1];
            result[4] = hexesTest[row+1][col];
            result[5] = hexesTest[row][col-1];
        }
        return result;
    }

    public static void main(String[] args) {
        populateHexTest();
        for (int i = 0;i <= 5;i++) {
            System.out.print(adjacentHexes(hexesTest[2][2])[i].getRow() + " ");
            System.out.println(adjacentHexes(hexesTest[2][2])[i].getCol());
        }
    }

}
