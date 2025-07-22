import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FactorialServlet")
public class FactorialServlet extends HttpServlet {

  /**
   * Handles HTTP GET requests to compute and display factorial values up to a given limit.
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();

    writer.println("<!DOCTYPE html>");
    writer.println("<html lang='en'>");
    writer.println("<head>");
    writer.println("<meta charset='UTF-8'>");
    writer.println("<title>Factorial Computation</title>");
    writer.println("<style>");
    writer.println("body { font-family: Arial, sans-serif; }");
    writer.println("table { border-collapse: collapse; width: 60%; margin: 20px 0; }");
    writer.println("th, td { border: 1px solid #000; padding: 8px 12px; text-align: center; }");
    writer.println("th { background-color: #f2f2f2; }");
    writer.println("</style>");
    writer.println("</head>");
    writer.println("<body>");
    writer.println("<h1>Factorial Results</h1>");

    try {
      String limitParameter = request.getParameter("limit");
      int limit = Integer.parseInt(limitParameter);

      if (limit < 0) {
        writer.println("<p style='color:red;'>Error: Please enter a non-negative integer.</p>");
      } else {
        writer.println("<table>");
        writer.println("<tr><th>Number</th><th>Factorial</th></tr>");

        for (int i = 0; i <= limit; i++) {
          BigInteger factorial = calculateFactorial(i);
          writer.println("<tr><td>" + i + "</td><td>" + factorial + "</td></tr>");
        }

        writer.println("</table>");
      }
    } catch (NumberFormatException e) {
      writer.println("<p style='color:red;'>Invalid input format. Please enter a valid numeric value.</p>");
    } finally {
      writer.println("</body>");
      writer.println("</html>");
      writer.close();
    }
  }

  /**
   * Computes the factorial of a non-negative integer.
   *
   * @param n the integer whose factorial is to be computed
   * @return the factorial as a BigInteger
   */
  private BigInteger calculateFactorial(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }

  /**
   * Redirects POST requests to GET handler.
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
