package edu.uclm.es.ds2021;

public class Producto {

	// ¡¡ En el proyecto carreful, los atributos están puestos a String /
	// VARCHAR(255) porque Macario no se complicó la vida en controlar y parsear a
	// float/double en el add() del product.js !!.
	
	private String nombre;
	private String precio;

	public Producto(String nombre, String precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String toString() {
		return "Producto: " + this.nombre + " - Precio: " + this.precio + " €.";
	}

}
