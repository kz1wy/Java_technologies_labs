import java.util.Scanner;

public class Lab1_1 {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("invalid expression");
      return;
    }
    
    String expression = String.join(" ", args);
    Scanner scanner = new Scanner(expression);
    double result = scanner.nextDouble();
    
    while (scanner.hasNext()) {
      String op = scanner.next();
      double operand = scanner.nextDouble();
      
      switch (op) {
        case "+":
          result += operand;
          break;
        case "-":
          result -= operand;
          break;
        case "x":
          result *= operand;
          break;
        case "/":
          result /= operand;
          break;
        default:
          System.out.println("Unsupported operator");
          return;
      }
    }
    
    System.out.println(result);
  }
}