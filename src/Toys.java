public class Toys {
    protected int count;
    protected String name;

    public Toys(String name, int count) {
        this.count = count;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    protected void Win(){
        this.count--;
    }

    protected void add(int count){
        this.count += count;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.count + "шт." ;
    }
}
