import java.util.*;

public class Main {


    public static void main(String[] args) {
        Main main = new Main();
        Integer[] nums = new Integer[]   { 4, 2, 7, 8, 2, 9, 43, 7, 1};
        int[] klein = new int[] { 1, 2, 3, 4};
        int[] lala = new int[] {1, 1, 2, 2, 3, 3, 3, 3};
        List<Integer> tmp = Arrays.asList(nums);
        List<Integer> list = new LinkedList<>(tmp);

        HashMap<String, Integer> test = new HashMap<>();
        test.put("cdcd", 1);
        test.put("dcdc", 2);
        for(String s : test.keySet()) {
            System.out.println(s + ": " + test.get(s));
        }
    }



    /**
     *
     * @param board fertiges oder nicht fertiges Sudoku
     * @return true, wenn das Sudoku so moeglich ist, sonst false
     */
    static boolean isValidSudoku(char[][] board) {
        // wahrscheinlich eine etwas dumme rangehensweise, aber trotzdem relativ schnell
        Map<String, LinkedList<Character>> map = new HashMap<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                char cur = board[i][j];
                if(cur == '.') continue;
                String row = "Row" + i;
                String col = "Col" + j;
                String box = "Box" + box(i, j);
                if(!map.containsKey(row)) map.put(row, new LinkedList<>());
                else if(map.get(row).contains(cur)) return false;
                if(!map.containsKey(col)) map.put(col, new LinkedList<>());
                else if(map.get(col).contains(cur)) return false;
                if(!map.containsKey(box)) map.put(box, new LinkedList<>());
                else if(map.get(box).contains(cur)) return false;
                map.get(row).add(cur);
                map.get(col).add(cur);
                map.get(box).add(cur);
            }
        }
        return true;
    }

    /**
     * Hilfsfunktion fuer isValidSudoku(char[][] board)
     * @param i Reihe des Sudokus
     * @param j Spalte des Sudokus
     * @return Die Box, in der man sich gerade bei (i, j) befindet
     */
    static int box(int i, int j){
        if(i <= 2){
            if(j <= 2) return 1;
            if(j <= 5) return 2;
            else return 3;
        }else if(i <= 5){
            if(j <= 2) return 4;
            if(j <= 5) return 5;
            else return 6;
        }else{
            if(j <= 2) return 7;
            if(j <= 5) return 8;
            else return 9;
        }
    }

    /**
     * Sortiert ein 2D Array in place
     * @param array 2D array, dass sortiert werden soll
     */
    private void sort2dArray(int[][] array){
        for(int[] a : array){
            Arrays.sort(a);
        }
    }

    /**
     *
     * @param n Integer, der umgedreht werden soll
     * @return Die umgedrehte Zahl
     */
    static int reverseInt(int n){
        return Integer.parseInt(new StringBuilder(n).reverse().toString());
    }

    /**
     * Zeichnet eine Sanduhr mit gegebener maximalen Breite
     * @param n Breite
     */
    static void sanduhr(int n){
        int current = 0;
        for(int i = n; i > 0; i--){
            current = current + 2 * i;
        }
        current--;
        int breite = n;
        int tmp = n;
        int abstand = 0;
        // 1. Hälfte
        for(int i = n; i > 0; i--){
            for(int j = 0; j < abstand; j++){
                System.out.print(" ");
            }
            while(breite > 0){
                System.out.print(current-- + " ");
                breite--;
            }

            System.out.println();
            abstand++;
            tmp--;
            breite = tmp;
        }
        // 2. Hälfte
        breite = n - 1;
        tmp = breite;
        abstand = n - 2;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < abstand; j++){
                System.out.print(" ");
            }
            while(breite <= n){
                System.out.print(current-- + " ");
                breite++;
            }
            System.out.println();
            abstand--;
            tmp--;
            breite = tmp;
        }
    }

    /**
     *
     * @param n Hoehe des Pascaldreiecks
     * @return eine 2D Liste bestehend aus den Werten des Dreiecks
     */
    static List<List<Integer>> pascalTriangle(int n) {
        List<List<Integer>> ergebnis = new LinkedList<>();
        if(n <= 0)
            return ergebnis;
        for(int i = 0; i < n; i++){
            List<Integer> row = new LinkedList<>();
            for(int j = 0; j < i + 1; j++){
                if(j == 0 || j == i){
                    row.add(1);
                    System.out.print("1");
                }else{
                    int tmp = ergebnis.get(i - 1).get(j - 1) + ergebnis.get(i - 1).get(j);
                    row.add(tmp);
                    System.out.print(tmp);
                }
            }
            System.out.print("\n");
            ergebnis.add(row);
        }

        return ergebnis;
    }

    /**
     * Bubblesort Algorithmus in place
     * @param arr Arrays, dass sortiert werden soll
     * @return das sortierte Array
     */
    static int[] bubblesort(int[] arr){
        // Bubblesort Algorithmus
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int merker = arr[j];
                    arr[j] = arr[i];
                    arr[i] = merker;
                }
            }
        }
        return arr;
    }

    /**
     * Rekursive Binaersuche
     * @param arr Array, indem der Wert @value gesucht werden soll
     * @param value zu suchender Wert
     * @param anfang linke Grenze fuer Rekursion
     * @param ende rechte Grenze fuer Rekursion
     * @return true, wenn der Wert in dem Array zu finden ist, sonst false
     */
    static boolean containsBinarySearchRecursive(int[] arr, int value, int anfang, int ende){
        int mid = (ende + anfang) / 2;
        if(anfang >= ende)
            return false;
        if(arr[mid] == value)
            return true;
        else if(arr[mid] > value)
            return containsBinarySearchRecursive(arr, value, anfang, mid - 1);
        else
            return containsBinarySearchRecursive(arr, value, mid + 1, ende);
    }

    /**
     * Iterative Binaersuche
     * @param arr Array, indem der Wert @value gesucht werden soll
     * @param value zu suchender Wert
     * @return true, wenn der Wert in dem Array zu finden ist, sonst false
     */
    static boolean containsBinarySearch(int[] arr, int value){
        // Iterative Binärsuche
        int anfang = 0;
        int ende = arr.length;
        int mid;
        while(anfang < ende){
            mid = (ende + anfang) / 2;
            if(arr[mid] == value)
                return true;
            else if(arr[mid] > value)
                ende = mid - 1;
            else
                anfang = mid + 1;
        }
        return false;
    }

    /**
     * sortiert alle geraden Werte an den Anfang des Arrays
     * @param arr zu sortierende Array
     * @return das sortierte Array
     */
    static int[] evenOdd(int[] arr){
        int[] output = new int[arr.length];
        int zaehler = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                output[zaehler] = arr[i];
                zaehler++;
            }
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0){
                continue;
            }
            output[zaehler] = arr[i];
            zaehler++;
        }
        return output;
    }

    /**
     * Algorithmus von Floyd, der die kuerzesten Strecken eines Graphen findet und aktualisiert
     * @param g Graph in Form eines double Arrays
     */
    static void floydify(double[][] g){
        // Algorithmus von Floyd

        int n = g.length;
        // druchlaufe alle Knoten vi und vj:
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    double ueber1 = 0;
                    double ueber2 = 0;
                    if(g[j][k] != 0)
                        ueber1 = g[j][k];
                    if(g[j][i] != 0 && g[i][k] != 0){
                        ueber2 = g[j][i] + g[i][k];
                    }
                    if(ueber1 == 0){
                        g[j][k] = ueber2;
                        continue;
                    }
                    if(ueber2 == 0){
                        g[j][k] = ueber1;
                        continue;
                    }
                    if(ueber1 == 0 && ueber2 == 0)
                        continue;
                    g[j][k] = Math.min(ueber1, ueber2);
                }
            }
        }
    }

    /**
     * Testet, ob die Zahl eine Primzahl ist
     * @param n zu ueberpruefende Zahl
     * @return true, wenn es eine Primzahl ist, sonst false
     */
    static boolean isPrime(int n){
        if(n == 1) return false;
        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Dreht das Array in place um
     * @param array zu drehende Array
     */
    static void reverseArray(int[] array){
        int i = 0, j = array.length - 1;
        while(i < j){
            array[i] = array[i] ^ array[j];
            array[j] = array[i] ^ array[j];
            array[i] = array[i] ^ array[j];
            i++;
            j--;
        }
    }

    /**
     * Sucht die laengste Sequenz von 0en hintereinander
     * @param array durchsuchende Array
     * @return Laengste Sequenz von 0en
     */
    static int laengsteSequenzVonNullern(int[] array){
        int ergebnis = 0;
        int merker = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0)
                merker++;
            if(merker > ergebnis)
                ergebnis = merker;
            if(array[i] != 0)
                merker = 0;
        }
        return ergebnis;
    }

    /**
     *
     * @param x erster Wert
     * @param y zweiter Wert
     * @return den groeßten gemeinsamen Teiler von @x und @y
     */
    static int ggT(int x, int y){
        // berechnet den groessten gemeinsamen Teiler zweier Zahlen
        if(x == 0)
            return y;
        if(y == 0)
            return x;
        if(x > y)
            return ggT(x - y, y);
        return ggT(x, y - x);
    }
}
