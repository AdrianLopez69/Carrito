package Servlet;

public class Productos {
    private String nom;
    private double valoruni;
    private int cant;

    //Inicializamos las variables
    public Productos(String nom, double valoruni, int cant) {
        this.nom = nom;
        this.valoruni = valoruni;
        this.cant = cant;
    }

    //Metodos para obtener el valor de las variable
    public String getNom() {
        return nom;
    }
    public double getValoruni() {
        return valoruni;
    }
    public int getCant() {
        return cant;
    }

    // Método para calcular el valor total
    public double getValorTotal() {
        return valoruni * cant;
    }
}
