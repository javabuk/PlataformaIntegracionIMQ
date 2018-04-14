package org.correlaciones.services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.correlaciones.model.Correlacion;
import org.correlaciones.repository.RepositorioFicheroExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("salidaFicheroSqlImpl")
public class SalidaFicheroSqlImpl implements SalidaFicheroSql {
	
	@Value( "${sql.rutaDirectorioSalida}")
	private String rutaSalida;
	
	@Autowired
	private RepositorioFicheroExcel repositorioFichero;
	
	private List<Correlacion> datosCorrelaciones;
	private String label;

	public SalidaFicheroSqlImpl(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.services.SalidaFicheroSql#generarFicherosTexto(java.util.List, java.lang.String)
	 */
	@Override
	public void generarFicherosTexto(   String label ) throws FileNotFoundException {
		this.datosCorrelaciones = repositorioFichero.recuperarCodigosLabImq(label);
		this.label = label;
		generarFicheroCorrelaciones();
		generarFicheroCodigosA();
		generarFicheroCodigosB();
	}
	
	private void generarFicheroCorrelaciones() throws FileNotFoundException {
		@SuppressWarnings("resource")
		PrintWriter out = new PrintWriter(rutaSalida + "\\Correlaciones_"+ label +".sql");
		String contenidoFichero = new String();
		for (Iterator iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			
			contenidoFichero += contenidoFichero.format("INSERT INTO PSI_CORRELACIONES ( CODIGOA, SISTEMAA, TIPOA, CODIGOB, SISTEMAB, TIPOB) VALUES ( '%s', '%s', '%s', '%s', '%s', '%s' );%n", correlacion.getCodigoA(), correlacion.getSistemaA(), correlacion.getTipoA(), correlacion.getCodigoB(), correlacion.getSistemaB(), correlacion.getTipoB());
			
		}
		
		out.print(contenidoFichero);
		
		out.close();
	}
	
	private void generarFicheroCodigosA() throws FileNotFoundException {
		@SuppressWarnings("resource")
		PrintWriter out = new PrintWriter(rutaSalida + "\\CodigosINFO_"+ label +".sql");
		String contenidoFichero = new String();
		for (Iterator iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			
			contenidoFichero += contenidoFichero.format("INSERT INTO PSI_CODIGOS (CODIGO, DESCRIPCION, SISTEMA, TIPO) VALUES ( '%s', '%s', '%s', '%s' );%n ", correlacion.getCodigoA(), correlacion.getDescripcion(), correlacion.getSistemaA(), correlacion.getTipoA());
			
		}
		
		out.print(contenidoFichero);
		
		out.close();
	}
	
	private void generarFicheroCodigosB() throws FileNotFoundException {
		@SuppressWarnings("resource")
		PrintWriter out = new PrintWriter(rutaSalida + "\\CodigosLaboratorio_"+ label +".sql");
		String contenidoFichero = new String();
		for (Iterator iterator = datosCorrelaciones.iterator(); iterator.hasNext();) {
			Correlacion correlacion = (Correlacion) iterator.next();
			
			contenidoFichero += contenidoFichero.format("INSERT INTO PSI_CODIGOS (CODIGO, DESCRIPCION, SISTEMA, TIPO) VALUES ( '%s', '%s', '%s', '%s' );%n ", correlacion.getCodigoB(), correlacion.getDescripcion(), correlacion.getSistemaB(), correlacion.getTipoB());
			
		}
		
		out.print(contenidoFichero);
		
		out.close();
	}	
	

}
