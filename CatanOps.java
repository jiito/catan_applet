// Operations for Catan
//
// CS201 final project - Catan

public class CatanOps {

    //test 2D hex array:
    public static Hex[][] hexesTest = new Hex[5][5];

    public static void populateHexTest() {
        for (int i = 0; i <= 4; i++) {
            for (int x = 0; x <= 4; x++) {
                Hex hex = new Hex(0, x*10, i*2, x, i, 6,false);// change to be random
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

    //takes in a vertex, returns a list of all adjacent paths.
    public static int[] adjacentPathsToVertex(int vertexID) {
        String numString = Integer.toString(vertexID);
        int[] result = new int[3];
        Hex a = hexesTest[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        Hex b = hexesTest[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex c = hexesTest[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        result[0] = makePathID(a,b);
        result[1] = makePathID(b,c);
        result[2] = makePathID(a,c);
        return result;
    }

    //takes in a hex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToHex(Hex h) {
        Hex[] adjacentHexes = adjacentHexesToHex(h);
        int[] result = new int[6];
        for (int i = 0; i<=4; i++) {
            result[i] = makeVertexID(h,adjacentHexes[i],adjacentHexes[i+1]);
        }
        result[5] = makeVertexID(h,adjacentHexes[0],adjacentHexes[5]);
        return result;
    }


    //takes in a vertex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToVertex(int vertexID) {
        int[] result = new int[3];
        String numString = Integer.toString(vertexID);
        Hex a = hexesTest[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        Hex b = hexesTest[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex c = hexesTest[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        Hex[] originalHexes = new Hex[3];
            originalHexes[0] = a;
            originalHexes[1] = b;
            originalHexes[2] = c;
        Hex[] coA = coAdjacentHexes(a,b);
        Hex pointHexA = nonHex(originalHexes,coA);
        Hex[] coB = coAdjacentHexes(b,c);
        Hex pointHexB = nonHex(originalHexes,coB);
        Hex[] coC = coAdjacentHexes(a,c);
        Hex pointHexC = nonHex(originalHexes,coC);
            result[0] = makeVertexID(a,b,pointHexA);
            result[1] = makeVertexID(b,c,pointHexB);
            result[2] = makeVertexID(a,c,pointHexC);
        return result;
    }

    //takes in an x and a y coordinate, returns the nearest 3 hexes.
    public static Hex[] nearestThreeHexes(int x, int y) {
        Hex[] result = new Hex[3];
        Hex[] flatList = new Hex[25];
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                flatList[(i+j)+(i*4)] = hexesTest[j][i];
            }
        }
        sortHexesByDist(flatList,x,y);
        result[0] = flatList[0];
        result[1] = flatList[1];
        result[2] = flatList[2];
        return result;
    }


    //takes in an x and a y coordinate, returns the nearest 2 hexes.
    public static Hex[] nearestTwoHexes(int x, int y) {
        Hex[] result = new Hex[2];
        Hex[] flatList = new Hex[25];
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                flatList[(i+j)+(i*4)] = hexesTest[j][i];
            }
        }
        sortHexesByDist(flatList,x,y);
        result[0] = flatList[0];
        result[1] = flatList[1];
        return result;
    }

    //takes in three hexes, returns their average coordinates.
    public static int[] averageCoordinate(Hex a, Hex b, Hex c) {
        int[] result = new int[2];
        result[0] = ((a.getRow() + b.getRow() + c.getRow())/3);
        result[1] = ((a.getCol() + b.getCol() + c.getCol())/3);
        return result;
    }

    //takes in a Hex and a coordinate, returns the distance.
    public static double distanceToHex(Hex h, int x, int y) {
        double x1 = x;
        double y1 = y;
        double x2 = h.getX();
        double y2 = h.getY();
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }


    //takes in two ints and joins them like a string. 1 and 2 would return 12.
    public static int splice(int a, int b) {
        String c = Integer.toString(a) + Integer.toString(b);
        return Integer.parseInt(c);
    }


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


    //takes in three Hexes, returns the vertex in between them.
    public static int makeVertexID(Hex a, Hex b, Hex c) {
        int[] numArray = new int[3];
        numArray[0] = splice(a.getRow(),a.getCol());
        numArray[1] = splice(b.getRow(),b.getCol());
        numArray[2] = splice(c.getRow(),c.getCol());
        bubbleSort(numArray);
        return splice(splice(numArray[0],numArray[1]),numArray[2]);
    }


    //takes in two Hexes, returns the path in between them.
    public static int makePathID(Hex a, Hex b) {
        int[] numArray = new int[2];
        numArray[0] = splice(a.getRow(),a.getCol());
        numArray[1] = splice(b.getRow(),b.getCol());
        bubbleSort(numArray);
        return splice(numArray[0],numArray[1]);
    }


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

    //sorts a Hex array by distance to a given point
    public static void sortHexesByDist(Hex[] array, int x, int y) {
    boolean swapped = true;
    int j = 0;
    Hex tmp;
    while (swapped) {
        swapped = false;
        j++;
        for (int i = 0; i < array.length - j; i++) {
            if (distanceToHex(array[i],x,y) > distanceToHex(array[i + 1],x,y)) {
                tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
                swapped = true;
                }
            }
        }
    }


    //takes in two lists of hexes, returns the hex from the second
    //list that is not in the first list
    public static Hex nonHex(Hex[] noHex, Hex[] hexOptions) {
        if (! containsHex(noHex, hexOptions[0])) {
            return hexOptions[0];
        }
        else {
            return hexOptions[1];
        }
    }


    //contains method for Hexes.
    public static boolean containsHex(Hex[] hexList, Hex h) {
        for (int i = 0; i <= 2; i++) {
            if (hexList[i] == h) {
                return true;
            }
        }
        return false;
    }

    //main method - for testing purposes only
    public static void main(String[] args) {
        populateHexTest();
        System.out.print("abc".toCharArray()[0]);
        for (int i = 0;i <= 5;i++) {
            System.out.print(adjacentHexesToHex(hexesTest[2][2])[i].getRow() + " ");
            System.out.println(adjacentHexesToHex(hexesTest[2][2])[i].getCol());
        }
        System.out.println("ADJACENT HEXES TO VERTEX 212232:");
        for (int i = 0;i <= 2;i++) {
            System.out.print(adjacentHexesToVertex(212232)[i].getRow() + " ");
            System.out.println(adjacentHexesToVertex(212232)[i].getCol());
        }
        System.out.println("CO-ADJACENT HEXES TO [2,2] AND [1,1]:");
        for (int i = 0;i <= 1;i++) {
            System.out.print(coAdjacentHexes(hexesTest[2][2],hexesTest[1][1])[i].getRow() + " ");
            System.out.println(coAdjacentHexes(hexesTest[2][2],hexesTest[1][1])[i].getCol());
        }
        System.out.println("SPLICE TEST:");
        System.out.println(splice(1,2));
        System.out.println("VERTEX ID TEST:");
        System.out.println(makeVertexID(hexesTest[2][2],hexesTest[1][1],hexesTest[2][1]));
        System.out.println("PATH ID TEST:");
        System.out.println(makePathID(hexesTest[2][2],hexesTest[1][1]));
        System.out.println("ADJACENT VERTICES TO PATH 2223:");
        for (int i = 0;i <= 1;i++) {
            System.out.println(adjacentVerticesToPath(2223)[i] + " ");
            System.out.println("");
        }
        System.out.println("ADJACENT PATHS TO PATH 2223:");
        for (int i = 0;i <= 3;i++) {
            System.out.print(adjacentPathsToPath(2223)[i] + " ");
            System.out.println("");
        }
        System.out.println("ADJACENT PATHS TO VERTEX 222332:");
        for (int i = 0;i <= 2;i++) {
            System.out.print(adjacentPathsToVertex(222332)[i] + " ");
            System.out.println("");
        }
        System.out.println("ADJACENT VERTICES TO HEX [2,2]:");
        for (int i = 0;i <= 5;i++) {
            System.out.print(adjacentVerticesToHex(hexesTest[2][2])[i] + " ");
            System.out.println("");
        }
        System.out.println("ADJACENT VERTICES TO VERTEX 222332:");
        for (int i = 0;i <= 2;i++) {
            System.out.print(adjacentVerticesToVertex(222332)[i] + " ");
            System.out.println("");
        }
        System.out.println("DISTANCE FROM 100,100 TO HEX [2,2]:");
            System.out.println(distanceToHex(hexesTest[2][2],100,100));
        System.out.println("NEAREST 3 HEXES TO 50,50:");
        for (int i = 0;i <= 2;i++) {
            System.out.print(nearestThreeHexes(50,50)[i].getRow() + " ");
            System.out.println(nearestThreeHexes(50,50)[i].getCol());
        }
        System.out.println("NEAREST 2 HEXES TO 50,50:");
        for (int i = 0;i <= 1;i++) {
            System.out.print(nearestTwoHexes(50,50)[i].getRow() + " ");
            System.out.println(nearestTwoHexes(50,50)[i].getCol());
        }
        System.out.println("AVERAGE COORD [2,2],[1,1],[2,1]");
    }

}
