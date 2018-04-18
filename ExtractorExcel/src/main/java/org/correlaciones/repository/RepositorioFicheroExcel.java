package org.correlaciones.repository;

import java.util.List;

import org.correlaciones.model.Correlacion;

public interface RepositorioFicheroExcel {

	List<Correlacion> recuperarCodigosLabImq(String codigoLaboratorio);
	
	List<Correlacion> recuperarCodigosCorrelaciones(String rutaFicheroExcel, String codigoLaboratorio);

}