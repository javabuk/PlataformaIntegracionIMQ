package org.correlaciones.repository;

import java.util.List;

import org.correlaciones.model.Correlacion;

public interface RepositorioCreacionInserts {

	String generarInsertsCorrelaciones(List<Correlacion> datosCorrelaciones);

	String generarInsertsCodigosA(List<Correlacion> datosCorrelaciones);

	String generarInsertsCodigosB(List<Correlacion> datosCorrelaciones);

}