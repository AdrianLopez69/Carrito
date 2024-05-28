package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Factura")
public class Factura extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Productos> carrito = (List<Productos>) session.getAttribute("carrito");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Factura</title>");
        //Estilos css
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("}");
        out.println("h1 {");
        out.println("    text-align: center;");
        out.println("}");
        out.println("table {");
        out.println("    width: 100%;");
        out.println("    border-collapse: collapse;");
        out.println("}");
        out.println("table, th, td {");
        out.println("    border: 1px solid black;");
        out.println("}");
        out.println("th, td {");
        out.println("    padding: 10px;");
        out.println("    text-align: left;");
        out.println("}");
        out.println("tfoot td {");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Factura</h1>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Nombre del Producto</th>");
        out.println("<th>Valor Unitario</th>");
        out.println("<th>Cantidad</th>");
        out.println("<th>Valor Total</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        double total = 0;

        if (carrito != null) {
            // Recorrer la lista de productos en el carrito
            for (Productos producto : carrito) {
                out.println("<tr>");
                out.println("<td>" + producto.getNom() + "</td>");
                out.println("<td>$" + producto.getValoruni() + "</td>");
                out.println("<td>" + producto.getCant() + "</td>");
                double valorTotalProducto = producto.getValorTotal();
                out.println("<td>$" + valorTotalProducto + "</td>");
                out.println("</tr>");
                total += valorTotalProducto;
            }
        } else {
            out.println("<tr>");
            out.println("<td colspan='4'>El carrito no tiene Productos</td>");
            out.println("</tr>");
        }

        out.println("<tr>");
        out.println("<td colspan='3'>Subtotal:</td>");
        out.println("<td>$" + String.format("%.2f", total) + "</td>");
        out.println("</tr>");
        double iva = total * 0.15;
        double totalConIVA = total + iva;
        out.println("<tr>");
        out.println("<td colspan='3'>IVA (15%):</td>");
        out.println("<td>$" +  String.format("%.2f", iva) + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan='3'>Total con IVA:</td>");
        out.println("<td>$" + String.format("%.2f", totalConIVA) + "</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<div style='text-align: center; margin-top: 20px;'>");
        out.println("<a href='index.html' style='text-decoration: none;'>");
        out.println("<button style='background-color: #4CAF50; color: blue; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; border: none; border-radius: 4px;'>");
        out.println("Volver al Inicio");
        out.println("</button>");
        out.println("</a>");
        out.println("</div>");
        out.println("</tbody>");
        out.println("</body>");
        out.println("</html>");

    }
}
