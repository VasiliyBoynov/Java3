import java.util.ArrayList;

public class Task1<T> {
    private T[] obj;

    public Task1(T[] obj) {
        this.obj = obj;
    }

    public void myChange(int ind1,int ind2){
        if ((this.obj.length<(Math.max(ind1,ind2)))||(Math.min(ind1,ind2)<0)){
            throw new ArrayIndexOutOfBoundsException();
        }
        T tmp = this.obj[ind1];
        this.obj[ind1]=this.obj[ind2];
        this.obj[ind2]=tmp;
    }

    public void  info() {
        for (int i = 0; i < obj.length; i++) {
            T t = obj[i];
            System.out.printf("ind %d, value : %s;%n",i,t);

        }
    }

    public ArrayList<T> toList(){
        ArrayList<T> list = new ArrayList<T>();
        for (T t : this.obj) {
            list.add(t);
        }
        return list;
    }
}
