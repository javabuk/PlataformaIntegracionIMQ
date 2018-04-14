package org.correlaciones.services;

import java.io.FileNotFoundException;
import java.util.List;

import org.correlaciones.model.Correlacion;

public interface SalidaFicheroSql {

	void generarFicherosTexto(String label) throws FileNotFoundException;

}