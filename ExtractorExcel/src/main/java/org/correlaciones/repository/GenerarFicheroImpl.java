package org.correlaciones.repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.stereotype.Repository;

@Repository("generarFicheroImpl")
public class GenerarFicheroImpl implements GenerarFichero {
	
	
	public GenerarFicheroImpl() {
		
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.repository.GenerarFichero#crearFichero(java.lang.String)
	 */
	@Override
	public PrintWriter crearFichero(String rutaFichero) throws FileNotFoundException {
		
		return new PrintWriter(rutaFichero);
		
	}
	

}
