package lcs;
import java.util.*;


public class LCS {
    //declaring variables
    private static String one;
    private static String two;
    private static int[][] c;
    //to store the arrows I used a 2d char array that uses D for the diagonal case
    //U for the up case, and H for the left case(in my head they correspond to diagonal, up, and horizontal... yes I realize that's illogical, no I dont care)
    private static char[][] b;

    //constructor to hold the 2 strings
    public LCS(String first, String second){
        one = first;
        two = second;
    }
    //this is to call the method based off the pseudocode, not really necessary, but I could 1 to 1 the pseudocode easier this way
    public static void lcsDynamicSol(){
        lcsLength(one, two);
    }
    //the length method from the pseudocode provided
    //takes string one and string two as arguments
    public static void lcsLength(String x, String y){
        int m = x.length();
        int n = y.length();
        //dynamically set the length of the arrays to 1 greater than the length of the Strings
        c = new int[m+1][n+1];
        b = new char[m+1][n+1];
        //fill in 0's for the first row and column
        for(int i = 0; i < m; i++){
            c[i][0] = 0;
        }
        for(int j = 0; j < n; j++){
            c[0][j] = 0;
        }
        //main nested loop to generate grids(2d arrays)
        for(int i = 1;i <= m;i++){
            for (int j = 1; j <= n; j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 'D';
                }else if(c[i-1][j] >= c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = 'U';
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = 'H';
                }
            }
        }
    }
    //called by runner, it sets up the printlcs method
    public static String getLCS(){
        StringBuilder sb = new StringBuilder();
        printLcs(one.length(), two.length(), sb);
        return sb.toString();
    }
    //doesnt actally print, just modifies the stringbuilder object, because... pass by reference for objects
    public static void printLcs(int i, int j, StringBuilder str){
        if(i == 0 || j == 0){
            return;
        }
        if(b[i][j] == 'D'){
            printLcs(i-1, j-1, str);
            str.append(one.charAt(i-1));//I used stringbuilder instead of print so it could be returnable
        }else if(b[i][j] == 'U'){
            printLcs(i-1, j, str);
        }else{
            printLcs(i, j-1, str);
        }
    }
}
