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
        String numString = Integer.toString(vertexID);
        result[0] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        result[1] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        result[2] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        return result;
    }

    //takes in a path, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToPath(int pathID) {
        String numString = Integer.toString(pathID);
        Hex[] adjacents = new Hex[2];
            adjacents[0] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
            adjacents[1] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex[] coAdjacents = new Hex[2];
            coAdjacents = coAdjacentHexes(adjacents[0],adjacents[1]);
        int[] result = new int[2];
            result[0] = makeVertexID(adjacents[0],adjacents[1],coAdjacents[0]);
            result[1] = makeVertexID(adjacents[0],adjacents[1],coAdjacents[1]);
        return result;
    }

    //takes in a path, returns a list of all adjacent paths.
    public static int[] adjacentPathsToPath(int pathID) {
        String numString = Integer.toString(pathID);
        Hex[] adjacents = new Hex[2];
            adjacents[0] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
            adjacents[1] = hexesTest[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex[] coAdjacents = new Hex[2];
            coAdjacents = coAdjacentHexes(adjacents[0],adjacents[1]);
        int[] result = new int[4];
        result[0] = makePathID(adjacents[0],coAdjacents[0]);
        result[1] = makePathID(adjacents[0],coAdjacents[1]);
        result[2] = makePathID(adjacents[1],coAdjacents[0]);
        result[3] = makePathID(adjacents[1],coAdjacents[1]);
        return result;
    }

/*

    //may not need this...
    //takes in a vertex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToVertex(int vertexID) {
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
        return Integer.parseInt(c);
    }

    //helper function:
    //takes in two adjacent Hexes, finds the Hexes that coexist in both of
    //their adjacency lists
    public static Hex[] coAdjacentHexes(Hex a, Hex b) {
        Hex[] result = new Hex[2];
        Hex[] adjacencyA = adjacentHexesToHex(a);
        Hex[] adjacencyB = adjacentHexesToHex(b);
        for (int i = 0; i <= 5; i++) {
            for (Hex x : adjacencyA) {
                if (x == adjacencyB[i]) {
                    if (result[0] == null) {
                        result[0] = adjacencyB[i];
                    }
                    else {
                        result[1] = adjacencyB[i];
                    }
                }
            }
        }
        return result;
    }

    //helper function:
    //takes in three Hexes, returns the vertex in between them.
    public static int makeVertexID(Hex a, Hex b, Hex c) {
        int[] numArray = new int[3];
        numArray[0] = splice(a.getRow(),a.getCol());
        numArray[1] = splice(b.getRow(),b.getCol());
        numArray[2] = splice(c.getRow(),c.getCol());
        bubbleSort(numArray);
        return splice(splice(numArray[0],numArray[1]),numArray[2]);
    }

    //helper function:
    //takes in two Hexes, returns the path in between them.
    public static int makePathID(Hex a, Hex b) {
        int[] numArray = new int[2];
        numArray[0] = splice(a.getRow(),a.getCol());
        numArray[1] = splice(b.getRow(),b.getCol());
        bubbleSort(numArray);
        return splice(numArray[0],numArray[1]);
    }

    //helper function:
    //sorts an int array.
    public static void bubbleSort(int[] array) {
    boolean swapped = true;
    int j = 0;
    int tmp;
    while (swapped) {
        swapped = false;
        j++;
        for (int i = 0; i < array.length - j; i++) {
            if (array[i] > array[i + 1]) {
                tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
                swapped = true;
                }
            }
        }
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
        System.out.println("CO-ADJACENT:");
        for (int i = 0;i <= 1;i++) {
            System.out.print(coAdjacentHexes(hexesTest[2][2],hexesTest[1][1])[i].getRow() + " ");
            System.out.println(coAdjacentHexes(hexesTest[2][2],hexesTest[1][1])[i].getCol());
        }
        System.out.println("SPLICE:");
        System.out.println(splice(1,2));
        System.out.println("VERTEX ID:");
        System.out.println(makeVertexID(hexesTest[2][2],hexesTest[1][1],hexesTest[2][1]));
        System.out.println("PATH ID:");
        System.out.println(makePathID(hexesTest[2][2],hexesTest[1][1]));
        System.out.println("ADJACENT VERTICES TO PATH:");
        for (int i = 0;i <= 1;i++) {
            System.out.println(adjacentVerticesToPath(2223)[i] + " ");
            System.out.println("");
        }
        System.out.println("ADJACENT PATHS TO PATH:");
        for (int i = 0;i <= 3;i++) {
            System.out.print(adjacentPathsToPath(2223)[i] + " ");
            System.out.println("");
        }
    }

}
