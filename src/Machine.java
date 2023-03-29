import java.util.ArrayList;

public class Machine {
    ArrayList<Toys> assort = new ArrayList<>();

    protected void addToys(Toys item) {
        this.assort.add(item);
    }

    public void initToys(ArrayList<Toys> items) {
        for (Toys item : items) {
            addToys(item);
        }
    }
}
