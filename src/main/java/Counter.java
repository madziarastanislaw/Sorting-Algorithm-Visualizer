import javafx.collections.ObservableList;

public class Counter {
    private SortingAlgorithm sorter;
    public volatile int counterIf = 0;
    public volatile int counterGet = 0;

    public Counter(SortingAlgorithm sorter) {
        this.sorter = sorter;
    }

    public boolean cif(boolean b) {
        counterIf++;
        sorter.updateStepLabels();
        return b;
    }

    public int cget(ObservableList<Integer> l, int index){
        counterGet++;
        sorter.updateStepLabels();
        return l.get(index);
    }
}
