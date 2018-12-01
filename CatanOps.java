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
    public static Hex[] adjacentHexesToHex(Hex h) {
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


    //takes in a vertex, returns a list of all adjacent hexes.
    public static Hex[] adjacentHexesToVertex(int vertexID) {
        Hex[] result = new Hex[3];
        String numberString = Integer.toString(vertexID);
        result[0] = hexesTest[Integer.parseInt(Character.toString(numberString.charAt(0)))][Integer.parseInt(Character.toString(numberString.charAt(1)))];
        result[1] = hexesTest[Integer.parseInt(Character.toString(numberString.charAt(2)))][Integer.parseInt(Character.toString(numberString.charAt(3)))];
        result[2] = hexesTest[Integer.parseInt(Character.toString(numberString.charAt(4)))][Integer.parseInt(Character.toString(numberString.charAt(5)))];
        return result;
    }

/*

    //may not need this...
    //takes in a vertex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToVertex(int vertexID) {
        return;
    }

    //takes in a path, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToPath(int pathID) {
        return;
    }

    //takes in a path, returns a list of all adjacent paths.
    public static int[] adjacentPathsToPath(int pathID) {
        return;
    }

    //takes in a hex, returns an int list of the vertices.
    public static int[] adjacentVerticesToHex(Hex h) {
        return;
    }

    */

    //helper function:
    //takes in two ints and joins them like a string. 1 and 2 would return 12.
    public static int splice(int a, int b) {
        String c = Integer.toString(a) + Integer.toString(b);
        return Integer.parseInt("c");
    }

    public static void main(String[] args) {
        populateHexTest();
        System.out.print("abc".toCharArray()[0]);
        for (int i = 0;i <= 5;i++) {
            System.out.print(adjacentHexesToHex(hexesTest[2][2])[i].getRow() + " ");
            System.out.println(adjacentHexesToHex(hexesTest[2][2])[i].getCol());
        }
        System.out.println("VERTEX:");
        for (int i = 0;i <= 2;i++) {
            System.out.print(adjacentHexesToVertex(212232)[i].getRow() + " ");
            System.out.println(adjacentHexesToVertex(212232)[i].getCol());
        }
    }

}
