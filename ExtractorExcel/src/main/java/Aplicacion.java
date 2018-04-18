import java.util.List;
import java.util.Scanner;

import org.correlaciones.configuration.Configuracion;
import org.correlaciones.model.CodigoCPT;
import org.correlaciones.model.CodigoGestor;
import org.correlaciones.model.Correlacion;
import org.correlaciones.repository.GestorPeticionesRepositorioImpl;
import org.correlaciones.repository.INFO33Repositorio;
import org.correlaciones.repository.RepositorioFicheroExcel;
import org.correlaciones.services.GenerarFicheroServicioImpl;
import org.correlaciones.services.SalidaFicheroSql;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Aplicacion {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new AnnotationConfigApplicationContext(Configuracion.class);
		
		SalidaFicheroSql servicio = appContext.getBean("salidaFicheroSqlImpl", SalidaFicheroSql.class);
		
		GenerarFicheroServicioImpl servicioGenerarFichero = appContext.getBean("generarFicheroServicioImpl", GenerarFicheroServicioImpl.class);
		
		INFO33Repositorio repositorioBBDD = appContext.getBean("info33RepositorioImpl", INFO33Repositorio.class);
		GestorPeticionesRepositorioImpl repositorioBBDDGestor = appContext.getBean("gestorPeticionesRepositorioImpl", GestorPeticionesRepositorioImpl.class);
		
		RepositorioFicheroExcel repositorioFicheroExcel = appContext.getBean("FicheroExcel17042018Impl", RepositorioFicheroExcel.class);
		
		
		// Recogemos la entrada por consola:
		Scanner entradaConsola = new Scanner(System.in);
		
//		System.out.println("El codigo del CPT:");
//		
//		String codigoCPT = entradaConsola.next();
		
		// E:\Proyectos\IMQ\GestorPeticiones\Subversion\Correlaciones\170418.xlsx
		// 1703
		System.out.println("Ruta del fichero excel:");
		String rutaficheroExcel = entradaConsola.next();
		System.out.println("Codigo laboratorio:");
		String codigoLaboratorio = entradaConsola.next();
		
		try {
			//servicio.generarFicherosTexto(RepositorioFicheroExcelImpl.CODIGOLABORATORIOAXPE);
			//servicioGenerarFichero.generarFicheroSqlCorrelaciones("E:\\area\\FicheroCorrelacion.sql", RepositorioFicheroExcelImpl.CODIGOLABORATORIOAXPE);
//			List<CodigoCPT> codigos = repositorioBBDD.consultaCPT(codigoCPT);
//			System.out.println(codigos.size());
//			if(codigos.size()>0) {
//				System.out.println(codigos.get(0).toString());
//			}
			
			//List<CodigoGestor> buscarCodigo = repositorioBBDDGestor.buscarCodigo("GLU", "MEDIKOSTA", "LAB");
			//System.out.println(buscarCodigo.size());
			
			List<Correlacion> codigosCorrelaciones = repositorioFicheroExcel.recuperarCodigosCorrelaciones(rutaficheroExcel, codigoLaboratorio);
			for (Correlacion correlacion : codigosCorrelaciones) {
				System.out.println(correlacion.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		System.out.println("alguna operacion mas:");
		String respuesta = entradaConsola.next();

	}

}
