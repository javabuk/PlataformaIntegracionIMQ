package org.correlaciones.repository;

import java.util.Iterator;
import java.util.List;

import org.correlaciones.model.Correlacion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("RepositorioCreacionInsertsImpl")
public class RepositorioCreacionInsertsImpl implements RepositorioCreacionInserts {
	
	@Value("${sql.inserts.correlaciones}")
	private String sqlcorrelaciones;
	
	@Value("${sql.inserts.codigos}")
	private String sqlcodigos;
	
	/* (non-Javadoc)
	 * @see org.correlaciones.repository.RepositorioCreacionInserts#generarInsertsCorrelaciones(java.util.List)
	 */
	@Override
	public String generarInsertsCorrelaciones(List<Correlacion> datosCorrelaciones) {		
		String contenidoFichero = new String();
		for (Iterator<Correlacion> iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			contenidoFichero += contenidoFichero.format(sqlcorrelaciones, correlacion.getCodigoA(), correlacion.getSistemaA(), correlacion.getTipoA(), correlacion.getCodigoB(), correlacion.getSistemaB(), correlacion.getTipoB());
		}
		return contenidoFichero;
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.repository.RepositorioCreacionInserts#generarInsertsCodigosA(java.util.List)
	 */
	@Override
	public String generarInsertsCodigosA(List<Correlacion> datosCorrelaciones) {
		String contenidoFichero = new String();
		for (Iterator iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			contenidoFichero += contenidoFichero.format(sqlcodigos, correlacion.getCodigoA(), correlacion.getDescripcion(), correlacion.getSistemaA(), correlacion.getTipoA());
		}
		return contenidoFichero;
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.repository.RepositorioCreacionInserts#generarInsertsCodigosB(java.util.List)
	 */
	@Override
	public String generarInsertsCodigosB(List<Correlacion> datosCorrelaciones) {
		String contenidoFichero = new String();
		for (Iterator iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			contenidoFichero += contenidoFichero.format(sqlcodigos, correlacion.getCodigoB(), correlacion.getDescripcion(), correlacion.getSistemaB(), correlacion.getTipoB());
		}
		return contenidoFichero;
	}

}
