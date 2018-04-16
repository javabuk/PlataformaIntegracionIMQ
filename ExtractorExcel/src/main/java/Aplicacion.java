import java.io.FileNotFoundException;

import org.correlaciones.configuration.Configuracion;
import org.correlaciones.repository.RepositorioFicheroExcelImpl;
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
		
		try {
			//servicio.generarFicherosTexto(RepositorioFicheroExcelImpl.CODIGOLABORATORIOAXPE);
			servicioGenerarFichero.generarFicheroSqlCorrelaciones("E:\\area\\FicheroCorrelacion.sql", RepositorioFicheroExcelImpl.CODIGOLABORATORIOAXPE);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

}
