package org.correlaciones.services;

import java.io.FileNotFoundException;

public interface SalidaFicheroSql {

	void generarFicherosTexto(String label) throws FileNotFoundException;

}