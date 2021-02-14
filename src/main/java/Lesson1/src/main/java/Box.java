import java.util.ArrayList;

public class Box<T extends Fruit> {
    //private T fruit;
    private String typeBox = "empty";
    private ArrayList<T> list;

    public Box() {
    list = new ArrayList<T>();
    }

    public void addFruit(T obj){
        if (this.list.size()==0) {
            this.list.add(obj);
            typeBox = obj.getClass().getName();
        } else if (typeBox.equals(obj.getClass().getName())){
            this.list.add(obj);
        } else {
            System.out.println("This Box from " + typeBox);
        }
    }

    public String getTypeBox() {
        return typeBox;
    }

    private void removeFruit(){
        if (this.list.size()==0) {
            return;
        }
        this.list.remove(0);
        if (this.list.size()==0) {
            this.typeBox="empty";
        }
    }

    public double getWeight(){
        if (this.list.size()==0) {
            return 0;
        }
        double weight=0;
        for (T t : list) {
            weight+=t.getWeight();
        }
        return weight;
    }

    public boolean compare(Box box){
        if (Math.abs(this.getWeight()- box.getWeight())<0.0001) return true;
        return false;
    }

    public void copyAllFruit(Box box){
        if (box.getTypeBox().equals("empty")||this.typeBox.equals(box.getTypeBox())){
            while(this.list.size()>0){
                box.addFruit(this.list.get(0));
                this.removeFruit();
            }
            System.out.println("copy fruits were successful");
        } else if (this.list.size()==0) {
            System.out.println("Input's box is empty");
        }else {
            System.out.println("Different types boxes");
        }

    }
}
