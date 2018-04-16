package org.correlaciones.services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.correlaciones.model.Correlacion;
import org.correlaciones.repository.GenerarFichero;
import org.correlaciones.repository.RepositorioCreacionInserts;
import org.correlaciones.repository.RepositorioFicheroExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("generarFicheroServicioImpl")
public class GenerarFicheroServicioImpl implements GenerarFicheroServicio {

	@Autowired
	GenerarFichero generarFichero;
	
	@Autowired
	RepositorioCreacionInserts repositorioCreacionInserts;
	
	@Autowired
	private RepositorioFicheroExcel repositorioFicheroExcel;
	
	public GenerarFicheroServicioImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.services.GenerarFicheroServicio#generarFicheroSql(java.lang.String, java.lang.String)
	 */
	@Override
	public void generarFicheroSql (String rutaFichero, String contenidoFichero) throws FileNotFoundException {
		
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
		
	}
	
	public void generarFicheroSqlCorrelaciones(String rutaFichero, String laboratorio) throws FileNotFoundException {
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosLabImq(laboratorio);
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCorrelaciones(recuperarCodigosLabImq);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}
	
	public void generarFicheroSqlCodigosA(String rutaFichero, String laboratorio) throws FileNotFoundException {
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosLabImq(laboratorio);
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCodigosA(recuperarCodigosLabImq);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}
	
	public void generarFicheroSqlCodigosB(String rutaFichero, String laboratorio) throws FileNotFoundException {
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosLabImq(laboratorio);
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCodigosB(recuperarCodigosLabImq);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}

}
