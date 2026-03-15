import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class HeapSort extends SortingAlgorithm {

    public HeapSort(ObservableList<Integer> list, int baseDelayMillis,
                    Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {

            for (int i = listSize / 2 - 1; i >= 0; i--) {
                heapify(listSize, i);
                sleep();
            }

            for (int i = listSize - 1; i > 0; i--) {
                sleep();
                highlightClear();
                swap(0, i);
                highlightSorted(i);
                sleep();
                heapify(i, 0);
            }

            highlightSorted(0);
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm HeapSort zakończył działanie."));
        }).start();
    }

    private void heapify(int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        highlightCursor(rootIndex);
        if (C.cif(left < heapSize)) {
            highlightCompareLeft(left);
            if (C.cif(C.cget(list, left) > C.cget(list, largest))) {
                largest = left;
            }
        }

        if (C.cif(right < heapSize)) {
            highlightCompareRigth(right);
            if (C.cif(C.cget(list, right) > C.cget(list, largest))){
                largest = right;
            }

        }

        if (C.cif(largest != rootIndex)) {
            sleep();
            swap(rootIndex, largest);
            sleep();
            highlightClear();
            heapify(heapSize, largest);
        }
    }
}