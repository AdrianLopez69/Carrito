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
        double iva = total * 0.12;
        double totalConIVA = total + iva;
        out.println("<tr>");
        out.println("<td colspan='3'>IVA (12%):</td>");
        out.println("<td>$" +  String.format("%.2f", iva) + "</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan='3'>Total con IVA:</td>");
        out.println("<td>$" + String.format("%.2f", totalConIVA) + "</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</tbody>");
        out.println("</body>");
        out.println("</html>");

    }
}
