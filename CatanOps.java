// Operations for Catan
//
// CS201 final project - Catan

import java.util.Arrays;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;

public class CatanOps {

    //takes in a Hex, outputs a list of adjacent hexes
    public static Hex[] adjacentHexesToHex(Hex h, Hex[][] hexes) {
        int row = h.getRow();
        int col = h.getCol();
        Hex[] result = new Hex[6];
        if (row % 2 == 0) {
            result[0] = hexes[row-1][col];
            result[1] = hexes[row-1][col+1];
            result[2] = hexes[row][col+1];
            result[3] = hexes[row+1][col+1];
            result[4] = hexes[row+1][col];
            result[5] = hexes[row][col-1];
        }
        else {
            result[0] = hexes[row-1][col-1];
            result[1] = hexes[row-1][col];
            result[2] = hexes[row][col+1];
            result[3] = hexes[row+1][col];
            result[4] = hexes[row+1][col-1];
            result[5] = hexes[row][col-1];
        }
        return result;
    }


    //takes in a vertex, returns a list of all adjacent hexes.
    public static Hex[] adjacentHexesToVertex(int vertexID, Hex[][] hexes) {
        Hex[] result = new Hex[3];
        String numString = Integer.toString(vertexID);
        result[0] = hexes[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        result[1] = hexes[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        result[2] = hexes[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        return result;
    }

    //takes in a path, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToPath(int pathID, Hex[][] hexes) {
        String numString = Integer.toString(pathID);
        Hex[] adjacents = new Hex[2];
            adjacents[0] = hexes[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
            adjacents[1] = hexes[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex[] coAdjacents = new Hex[2];
            coAdjacents = coAdjacentHexes(adjacents[0],adjacents[1],hexes);
        int[] result = new int[2];
            result[0] = makeVertexID(adjacents[0],adjacents[1],coAdjacents[0]);
            result[1] = makeVertexID(adjacents[0],adjacents[1],coAdjacents[1]);
        return result;
    }

    //takes in a path, returns a list of all adjacent paths.
    public static int[] adjacentPathsToPath(int pathID, Hex[][] hexes) {
        String numString = Integer.toString(pathID);
        Hex[] adjacents = new Hex[2];
            adjacents[0] = hexes[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
            adjacents[1] = hexes[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex[] coAdjacents = new Hex[2];
            coAdjacents = coAdjacentHexes(adjacents[0],adjacents[1],hexes);
        int[] result = new int[4];
        result[0] = makePathID(adjacents[0],coAdjacents[0]);
        result[1] = makePathID(adjacents[0],coAdjacents[1]);
        result[2] = makePathID(adjacents[1],coAdjacents[0]);
        result[3] = makePathID(adjacents[1],coAdjacents[1]);
        return result;
    }

    //takes in a vertex, returns a list of all adjacent paths.
    public static int[] adjacentPathsToVertex(int vertexID, Hex[][] hexes) {
        String numString = Integer.toString(vertexID);
        int[] result = new int[3];
        Hex a = hexes[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        Hex b = hexes[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex c = hexes[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        result[0] = makePathID(a,b);
        result[1] = makePathID(b,c);
        result[2] = makePathID(a,c);
        return result;
    }

    //takes in a hex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToHex(Hex h,Hex[][] hexes) {
        Hex[] adjacentHexes = adjacentHexesToHex(h,hexes);
        int[] result = new int[6];
        for (int i = 0; i<=4; i++) {
            result[i] = makeVertexID(h,adjacentHexes[i],adjacentHexes[i+1]);
        }
        result[5] = makeVertexID(h,adjacentHexes[0],adjacentHexes[5]);
        return result;
    }


    //takes in a vertex, returns a list of all adjacent vertices.
    public static int[] adjacentVerticesToVertex(int vertexID,Hex[][] hexes) {
        int[] result = new int[3];
        String numString = Integer.toString(vertexID);
        Hex a = hexes[Integer.parseInt(Character.toString(numString.charAt(0)))][Integer.parseInt(Character.toString(numString.charAt(1)))];
        Hex b = hexes[Integer.parseInt(Character.toString(numString.charAt(2)))][Integer.parseInt(Character.toString(numString.charAt(3)))];
        Hex c = hexes[Integer.parseInt(Character.toString(numString.charAt(4)))][Integer.parseInt(Character.toString(numString.charAt(5)))];
        Hex[] originalHexes = new Hex[3];
            originalHexes[0] = a;
            originalHexes[1] = b;
            originalHexes[2] = c;
        Hex[] coA = coAdjacentHexes(a,b,hexes);
        Hex pointHexA = nonHex(originalHexes,coA);
        Hex[] coB = coAdjacentHexes(b,c,hexes);
        Hex pointHexB = nonHex(originalHexes,coB);
        Hex[] coC = coAdjacentHexes(a,c,hexes);
        Hex pointHexC = nonHex(originalHexes,coC);
            result[0] = makeVertexID(a,b,pointHexA);
            result[1] = makeVertexID(b,c,pointHexB);
            result[2] = makeVertexID(a,c,pointHexC);
        return result;
    }

    //takes in an x and a y coordinate, returns the nearest 3 hexes.
    public static Hex[] nearestThreeHexes(int x, int y,Hex[][] hexes) {
        Hex[] result = new Hex[3];
        Hex[] flatList = new Hex[64];
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                flatList[(i+j)+(i*7)] = hexes[j][i];
            }
        }
        sortHexesByDist(flatList,x,y);
        result[0] = flatList[0];
        result[1] = flatList[1];
        result[2] = flatList[2];
        return result;
    }


    //takes in an x and a y coordinate, returns the nearest 2 hexes
    public static Hex[] nearestTwoHexes(int x, int y,Hex[][] hexes) {
        Hex[] result = new Hex[2];
        Hex[] flatList = new Hex[64];
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                flatList[(i+j)+(i*7)] = hexes[j][i];
            }
        }
        sortHexesByDist(flatList,x,y);
        result[0] = flatList[0];
        result[1] = flatList[1];
        return result;
    }

    //takes in a vertex, returns the x and y coords of that vertex
    public static int[] coordsOfVertex(int vertexID, Hex [][] hexes) {
        return averageCoordinate(adjacentHexesToVertex(vertexID, hexes)[0],adjacentHexesToVertex(vertexID, hexes)[1],adjacentHexesToVertex(vertexID, hexes)[2]);
    }


    //takes in three hexes, returns their average coordinates.
    public static int[] averageCoordinate(Hex a, Hex b, Hex c) {
        int[] result = new int[2];
        result[0] = ((a.getX() + b.getX() + c.getX())/3);
        result[1] = ((a.getY() + b.getY() + c.getY())/3);
        return result;
    }

    //takes in a Hex and a coordinate, returns the distance.
    public static int distanceToHex(Hex h, int x, int y) {
        double x1 = x;
        double y1 = y;
        double x2 = h.getX();
        double y2 = h.getY();
        double result = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
        return (int)result;
    }


    //takes in two ints and joins them like a string. 1 and 2 would return 12.
    public static int splice(int a, int b) {
        String c = Integer.toString(a) + Integer.toString(b);
        return Integer.parseInt(c);
    }


    //takes in two adjacent Hexes, finds the Hexes that coexist in both of
    //their adjacency lists
    public static Hex[] coAdjacentHexes(Hex a, Hex b,Hex[][] hexes) {
        Hex[] result = new Hex[2];
        Hex[] adjacencyA = adjacentHexesToHex(a,hexes);
        Hex[] adjacencyB = adjacentHexesToHex(b,hexes);
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
        Arrays.sort(numArray);
        return splice(splice(numArray[0],numArray[1]),numArray[2]);
    }


    //takes in two Hexes, returns the path in between them.
    public static int makePathID(Hex a, Hex b) {
        int[] numArray = new int[2];
        numArray[0] = splice(a.getRow(),a.getCol());
        numArray[1] = splice(b.getRow(),b.getCol());
        Arrays.sort(numArray);
        return splice(numArray[0],numArray[1]);
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


    //contains method for Hexes; checks if a Hex is in a Hex array.
    public static boolean containsHex(Hex[] hexList, Hex h) {
        for (int i = 0; i <= 2; i++) {
            if (hexList[i] == h) {
                return true;
            }
        }
        return false;
    }

}
