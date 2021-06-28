package tetris;
public class Tetris {
    public static void main(String[] args) {
        FigureField A = new FigureField(20,20);
        A.ShowField();
        if(!A.BrainsOn()) {
            System.out.println("Матрица не может быть заполнена.");
            return;
        }
        A.ShowField();
    } 
}
