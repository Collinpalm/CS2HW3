package lcs;
import java.util.*;


public class LCS {
    private static String one;
    private static String two;
    private static int lcsLen;
    private static int[][] c;
    private static char[][] b;


    public LCS(String first, String second){
        one = first;
        two = first;
    }

    public static void lcsDynamicSol(){
        lcsLength(one, two);
    }

    public static void lcsLength(String x, String y){
        int m = x.length();
        int n = y.length();
        c = new int[m][n];
        b = new char[m][n];
        for(int i = 0; i <= m; i++){
            c[i][0] = 0;
        }
        for(int i = 0; i <= n; i++){
            c[0][i] = 0;
        }
        for(int i = 1;i <= m;i++){
            for (int j = 1; j<= n; j++){
                if(x.charAt(i) == y.charAt(j)){
                    c[i][j] = c[i][j] +1;
                    b[i][j] = 'D';
                }else if(c[i-1][j]>=c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = 'U';
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = 'H';
                }
            }
        }
    }

    public static String getLCS(){
        StringBuilder sb = new StringBuilder();
        printLcs(one.length(), two.length(), sb);
        return sb.toString();
    }

    public static void printLcs(int i, int j, StringBuilder str){
        if(i == 0 || j == 0){
            return;
        }
        if(b[i][j] == 'D'){
            printLcs(i-1, j-1, str);
            str.append(one.charAt(i));
        }else if(b[i][j] == 'U'){
            printLcs(i-1, j, str);
        }else{
            printLcs(i, j-1, str);
        }
    }
}
