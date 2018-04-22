package org.correlaciones.services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.correlaciones.model.CodigoGestor;
import org.correlaciones.model.Correlacion;
import org.correlaciones.repository.GenerarFichero;
import org.correlaciones.repository.GestorPeticionesRepositorioImpl;
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
	
	@Autowired
	private GestorPeticionesRepositorioImpl repositorioGestorPeticiones; 
	
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
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosCorrelaciones("E:\\Proyectos\\IMQ\\GestorPeticiones\\Subversion\\Correlaciones\\ficheroCorrelaciones.xlsx",laboratorio);
		List<Correlacion> correlacionesNuevas = new ArrayList<>();
		for (Correlacion correlacion : recuperarCodigosLabImq) {
			List<Correlacion> correlacionExistente = repositorioGestorPeticiones.buscarCorrelacion(correlacion.getCodigoA(), correlacion.getSistemaA(), "LAB", correlacion.getCodigoB(), correlacion.getSistemaB(), "LAB");
			if(correlacionExistente==null || ( correlacionExistente!=null && correlacionExistente.size()==0 ) ) {				
				System.out.println("No existe el código " + correlacion.getCodigoA() + " Sistema " + correlacion.getSistemaA() + " CodigoB " + correlacion.getCodigoB() + " SistemaB " + correlacion.getSistemaB());
				correlacionesNuevas.add(correlacion);
			}else {
				System.out.println("Existe el código " + correlacion.getCodigoA() + " Sistema " + correlacion.getSistemaA() + " CodigoB " + correlacion.getCodigoB() + " SistemaB " + correlacion.getSistemaB());
			}
		}
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCorrelaciones(correlacionesNuevas);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}
	
	public void generarFicheroSqlCodigosA(String rutaFichero, String laboratorio) throws FileNotFoundException {
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosCorrelaciones("E:\\Proyectos\\IMQ\\GestorPeticiones\\Subversion\\Correlaciones\\ficheroCorrelaciones.xlsx",laboratorio);
		List<Correlacion> codigosNuevos = new ArrayList<>();
		for (Correlacion correlacion : recuperarCodigosLabImq) {
			List<CodigoGestor> codigos = repositorioGestorPeticiones.buscarCodigo(correlacion.getCodigoA(), correlacion.getSistemaA(), "LAB");
			if(codigos==null || ( codigos!=null && codigos.size()==0 ) ) {				
				System.out.println("No existe el código " + correlacion.getCodigoA() + " Sistema " + correlacion.getSistemaA());
				codigosNuevos.add(correlacion);
			}else {
				System.out.println("Existe el código " + correlacion.getCodigoA() + " Sistema " + correlacion.getSistemaA());
			}
		}
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCodigosA(codigosNuevos);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}
	
	public void generarFicheroSqlCodigosB(String rutaFichero, String laboratorio) throws FileNotFoundException {
		PrintWriter crearFichero = generarFichero.crearFichero(rutaFichero);
		List<Correlacion> recuperarCodigosLabImq = repositorioFicheroExcel.recuperarCodigosCorrelaciones("E:\\Proyectos\\IMQ\\GestorPeticiones\\Subversion\\Correlaciones\\ficheroCorrelaciones.xlsx",laboratorio);
		List<Correlacion> codigosNuevos = new ArrayList<>();
		for (Correlacion correlacion : recuperarCodigosLabImq) {
			List<CodigoGestor> codigos = repositorioGestorPeticiones.buscarCodigo(correlacion.getCodigoB(), correlacion.getSistemaB(), "LAB");
			if(codigos==null || ( codigos!=null && codigos.size()==0 ) ) {				
				System.out.println("No existe el código " + correlacion.getCodigoB() + " Sistema " + correlacion.getSistemaB());
				codigosNuevos.add(correlacion);
			}else {
				System.out.println("Existe el código " + correlacion.getCodigoB() + " Sistema " + correlacion.getSistemaB());
			}
		}
		String contenidoFichero = repositorioCreacionInserts.generarInsertsCodigosB(codigosNuevos);
		crearFichero.println(contenidoFichero);
		crearFichero.close();
	}

}
