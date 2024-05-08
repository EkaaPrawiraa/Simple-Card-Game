import java.util.ArrayList;

public class Ladang {
    public int row = 4;
    public int col = 5;
    public ArrayList<ArrayList<Object>> ladang = new ArrayList<ArrayList<Object>>(row);

    public Ladang() {
        for (int j = 0; j < row; j++) {
            ArrayList<Object> temp = new ArrayList<Object>(col);
            for (int i = 0; i < col; i++) {
                temp.add(null);
            }
            this.ladang.add(temp);
        }
    }

    public void printLadang() {
        for (ArrayList<Object> row : this.ladang) {
            System.out.println(row);
        }
    }

    public void setElement(Object elmt, int i, int j) {
        this.ladang.get(i).set(j, elmt);
    }

    public Object getElement(int i, int j) {
        return this.ladang.get(i).get(j);
    }

    public static void main(String[] args) {
        Ladang l = new Ladang();
        l.setElement("Hello", 0, 0);
        l.printLadang();
    }
}
