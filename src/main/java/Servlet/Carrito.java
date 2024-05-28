package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Carrito")
public class Carrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la sesion
        HttpSession session = req.getSession(true);

        //obtenemos una lista con los producto
        List<Productos> carrito = (List<Productos>) session.getAttribute("carrito");
        //Creamos una condicion para que en caso de no haber una lista
        // cree la lista
        if (carrito==null){
            carrito=new ArrayList<>();
            session.setAttribute("carrito",carrito);
        }

        //Obtenemos los Parametros del formulario
        String nombre = req.getParameter("nom");
        double ValorUnitario = Double.parseDouble(req.getParameter("valoruni"));
        int cantidad = Integer.parseInt(req.getParameter("cant"));

        //Creamos un nuevo producto
        Productos producto = new Productos(nombre, ValorUnitario, cantidad);

        // Agregamos el producto al carrito
        carrito.add(producto);

        // Redirigir al usuario a la página de factura
       resp.sendRedirect("Factura");




    }
}
