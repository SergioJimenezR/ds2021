package edu.uclm.es.ds2021;

import java.sql.SQLException;

public class ProductoDao {

	public static void crearTabla() {
		try {
			Agente.getAgente().create("CREATE TABLE product ( "
					+ "nombre VARCHAR(255) NOT NULL, "
					+ "codigo VARCHAR(255) NULL, "
					+ "precio VARCHAR(255) NULL, "
					+ "PRIMARY KEY (nombre));");
		} catch (SQLException sqlexc) {
			// No hacer nada.
		}
	}

	public static void insertarProducto(Producto p) throws SQLException {
		Agente.getAgente().insert("INSERT INTO product VALUES ('" + p.getNombre() + "', null, '" + p.getPrecio() + "');");
	}

}
