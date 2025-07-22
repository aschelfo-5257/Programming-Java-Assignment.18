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

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>Factorial Results</title>");
    out.println("<style>");
    out.println("table { border-collapse: collapse; width: 60%; margin: 20px 0; }");
    out.println("th, td { border: 1px solid #000; padding: 8px 12px; text-align: center; }");
    out.println("th { background-color: #f2f2f2; }");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Computed Factorials</h1>");

    try {
      String limitParam = request.getParameter("limit");
      int limit = Integer.parseInt(limitParam);

      if (limit < 0) {
        out.println("<p style='color:red;'>Input must be a non-negative integer. Please revise your entry.</p>");
      } else {
        out.println("<table border='1'>");
        out.println("<tr><th>Number</th><th>Factorial</th></tr>");

        for (int i = 0; i <= limit; i++) {
          BigInteger factorial = calculateFactorial(i);
          out.println("<tr><td>" + i + "</td><td>" + factorial + "</td></tr>");
        }

        out.println("</table>");
      }
    } catch (NumberFormatException e) {
      out.println("<p>Invalid input. Please enter a valid number.</p>");
    } finally {
      out.println("</body>");
      out.println("</html>");
      out.close();
    }
  }

  private BigInteger calculateFactorial(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }

  // Fallback POST to GET
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
