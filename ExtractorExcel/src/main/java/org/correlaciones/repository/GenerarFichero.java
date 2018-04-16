package org.correlaciones.repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public interface GenerarFichero {

	PrintWriter crearFichero(String rutaFichero) throws FileNotFoundException;

}