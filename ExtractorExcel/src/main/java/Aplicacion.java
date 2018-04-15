import java.io.FileNotFoundException;

import org.correlaciones.repository.RepositorioFicheroExcelImpl;
import org.correlaciones.services.SalidaFicheroSql;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"org.correlaciones"})
public class Aplicacion {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new AnnotationConfigApplicationContext(Configuracion.class);
		
		SalidaFicheroSql servicio = appContext.getBean("salidaFicheroSqlImpl", SalidaFicheroSql.class);
		
		try {
			servicio.generarFicherosTexto(RepositorioFicheroExcelImpl.CODIGOLABORATORIOAXPE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

}
