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
        out.println("<html>");
        out.println("<head>");
        out.println("<Title>Factorial Table</Title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Factorial Table</h1>");

        try {
          String limitParam = request.getParameter("limit");
          int limit = Integer.parseInt(limitParam);

          if (limit < 0) {
            out.println("<p>Please enter a non-negative number.</p>");
              } else {
            out.println("<table borders='1'>");
            out.println("<tr><th>Number</th><th>Factorial</th></tr>");

            for (int i = 0; i <= limit; i++) {
              BigInteger factorial = calculateFactorial(i);
              out.println("")
            }
            out.println("</table>");
              }
          }
        }
      }
  }
