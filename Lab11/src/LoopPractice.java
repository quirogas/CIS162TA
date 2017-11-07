import java.util.stream.IntStream;

/**
 * Created by Santiago on 11/6/17.
 */
public class LoopPractice {
    /**
     * Empty constructor.
     */
    private LoopPractice(){

    }

    /**
     * display the first 100 multiples of num (ten numbers per line).
     * @param num
     */
    public void displayMultiples(int num){
        IntStream.range(1, 101).forEach(i -> {
            System.out.print((i % 10 == 0)? Integer.toString(i * num) + "\n" :Integer.toString(i * num) + ", ");
        });
    }

    /**
     * Draw a rectangle of asterisks with the provided number of rows and columns.
     * @param rows
     * @param cols
     */
    public void drawRectangle(int rows, int cols){
        IntStream.range(0, rows).forEach(y ->{
            IntStream.range(0,cols).forEach(x -> System.out.print("#"));
            System.out.println();
        });
    }

    /**
     * Draw a triangle of asterisks with the provided number of rows.  See sample #1
     * @param rows
     */
    public void drawTriangle(int rows){
        IntStream.range(1, rows + 1).forEach(y ->{
            IntStream.range(0, y).forEach(x -> System.out.print("@"));
            System.out.println();
        });
    }

    /**
     * Draw a triangle of @s with the provided number of rows
     * @param rows
     */
    public void drawOtherTriangle(int rows){
        IntStream.range(1, rows + 1).forEach(y ->{
            IntStream.range(-1, rows - y).forEach(x -> System.out.print("@"));
            System.out.println();
        });
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        LoopPractice l = new LoopPractice();
        l.displayMultiples(3);
        l.drawRectangle(5,5);
        l.drawTriangle(5);
        l.drawOtherTriangle(5);
    }
}
