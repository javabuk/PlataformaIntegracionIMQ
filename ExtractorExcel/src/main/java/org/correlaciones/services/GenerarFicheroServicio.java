package org.correlaciones.services;

import java.io.FileNotFoundException;

public interface GenerarFicheroServicio {

	/**
	 * Metodo que genera un fichero, en el sistema de archivos, con un texto determinado
	 * @param rutaFichero
	 * @param contenidoFichero
	 * @throws FileNotFoundException
	 */
	void generarFicheroSql(String rutaFichero, String contenidoFichero) throws FileNotFoundException;
	
	public void generarFicheroSqlCorrelaciones(String rutaFichero, String laboratorio) throws FileNotFoundException;
	
	public void generarFicheroSqlCodigosA(String rutaFichero, String laboratorio) throws FileNotFoundException;
	
	public void generarFicheroSqlCodigosB(String rutaFichero, String laboratorio) throws FileNotFoundException;

}