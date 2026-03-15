import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {

    private final ArrayList<Integer> array = new ArrayList<>();
    private final ObservableList<Integer> copyList = FXCollections.observableList(array);

    public MergeSort(ObservableList<Integer> list, int baseDelayMillis,
                    Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {

            copyList.addAll(list);
            //for (int i = 0; i < listSize; i++) {}

            mergeSort(0, listSize - 1);
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm MergeSort zakończył działanie."));
        }).start();
    }

    private void merge(int left, int right, int mid){
        for (int i = left; i <= right; i++) { highlightSortedDel(i); }
        highlightCompare(left, right);
        int l = 0;
        int r = 0;
        int k = left;
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
        highlightCursor(k);
        while (l < lengthLeft && r < lengthRight){
            highlightCursor(k);
            sleep();
            if (C.cif(C.cget(copyList, left + l) < copyList.get(mid + 1 + r))){
                list.set(k++, copyList.get(left + l));
                l++;
            } else {
                list.set(k++, C.cget(copyList, mid + 1 + r));
                r++;
            }
            sleep();
            highlightSorted(k-1);
        }

        while (l < lengthLeft){
            highlightCursor(k);
            sleep();
            list.set(k++, C.cget(copyList, left + l));
            l++;
            sleep();
            highlightSorted(k-1);
        }

        while (r < lengthRight){
            highlightCursor(k);
            sleep();
            list.set(k++, C.cget(copyList, mid + 1 + r));
            r++;
            sleep();
            highlightSorted(k-1);
        }

        for (int i = left; i <= right; i++){
            copyList.set(i, C.cget(list, i));
        }
    }

    private void mergeSort(int left, int right){
        if (C.cif(left < right)) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, right, mid);
        }
    }
}
