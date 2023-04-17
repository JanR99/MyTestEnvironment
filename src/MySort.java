import java.util.*;

public class MySort {

    public static void radixSort(LinkedList<Integer> list){
        // RadixSort einer 10 Ziffer langen Zahl

        // Buckets erstellen
        LinkedList<Integer>[] bs = new LinkedList[10];
        for(int i = 0; i < 10; i++){
            bs[i] = new LinkedList<>();
        }

        // Für jede Ziffer
        for(int i = 0; i < 10; i++){
            // Für jeden Integer in der zu sortierenden Liste
            for(Integer x : list){
                // (Wert / Anzahl an Ziffer ^ Stelle) % 1ß
                // => Platz für den Wert finden
                int b = (int) (x / Math.pow(10, i) % 10);
                bs[b].add(x);
            }
            // Alle Werte sind in den Buckets, somit Liste leeren
            list.clear();
            // sortierten Werte wieder in die Liste und Buckets leeren
            for(int j = 0; j < bs.length; j++){
                list.addAll(bs[j]);
                bs[j].clear();
            }
            // => 10 mal für Anzahl der Ziffern
        }
    }

    public static void mergeSort(int[] a, int n){
        if(n < 2){
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for(int i = 0; i < mid; i++){
            l[i] = a[i];
        }
        for(int i = mid; i < n; i++){
            r[i] = a[i];
        }
        mergeSort(l, l.length);
        mergeSort(r, r.length);
        merge(a, l, r, l.length, r.length);
    }
    private static void merge(int[] a, int[] l, int[] r, int left, int right){
        int i = 0, j = 0, k = 0;
        while(i < left && j < right){
            if(l[i] <= r[i]){
                a[k++] = l[i++];
            }else{
                a[k++] = r[j++];
            }
        }
        while(i < left){
            a[k++] = l[i++];
        }
        while(j < right){
            a[k++] = r[j++];
        }
    }

    public static void quickSort(int[] elements, int l, int r){
        if(l >= r){
            return;
        }
        int pivot = partition(elements, l, r);
        quickSort(elements, l, pivot - 1);
        quickSort(elements, pivot + 1, r);
    }
    private static int partition(int[] elements, int l, int r){
        int pivot = elements[r];
        int i = l;
        int j = r - 1;
        while(i < j){
            while(elements[i] < pivot)
                i++;
            while(j > l && elements[j] >= pivot)
                j--;
            if(i < j){
                elements[i] = elements[i] ^ elements[j];
                elements[j] = elements[i] ^ elements[j];
                elements[i] = elements[i] ^ elements[j];
                i++;
                j--;
            }
        }
        if(i == j && elements[i] < pivot)
            i++;
        if(elements[i] != pivot){
            elements[i] = elements[i] ^ elements[r];
            elements[r] = elements[i] ^ elements[r];
            elements[i] = elements[i] ^ elements[r];
        }
        return i;
    }
    
}
