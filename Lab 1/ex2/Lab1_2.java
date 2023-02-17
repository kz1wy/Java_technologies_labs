import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;
public class Lab1_2 {
    public static void main(String[] args) {
      int[] a = {1, 1, 1, 3};
      int[] b = {2, 2, 2, 2};
      
      ArrayOutput.print(a);
      ArrayOutput.print(b);
      
      int[] c = ArrayHandler.merge(a, b);
      ArrayOutput.print(c);

      ArrayHandler.sort(c);
      ArrayOutput.print(c);
      
      ArrayOutput.write(c, "output.txt");
    }
}