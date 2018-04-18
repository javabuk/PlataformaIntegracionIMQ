package org.correlaciones.repository;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.correlaciones.model.Correlacion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

//@Repository("repositorioFicheroExcelImpl")
public class RepositorioFicheroExcelImpl implements RepositorioFicheroExcel {
	
	
	@Value("${excel.rutaFichero}")
	private String rutaFicheroExcel;
	
	private FileOutputStream ficheroExcel;
	private Workbook libroTrabajoExcel;
	
	public static String CODIGOLABORATORIOIMQ = "1701";
	public static String CODIGOLABORATORIOAXPE = "1703";
	public static String CODIGOLABORATORIOLAFITA = "1704";

	public RepositorioFicheroExcelImpl() throws IOException, EncryptedDocumentException, InvalidFormatException {
		super();
		//leerFicheroExcel();		
	}
	
	private void leerFicheroExcel() throws IOException, EncryptedDocumentException, InvalidFormatException {
		libroTrabajoExcel = new XSSFWorkbook();
		libroTrabajoExcel =  WorkbookFactory.create(new File(rutaFicheroExcel));
	}
	
	/* (non-Javadoc)
	 * @see org.correlaciones.repository.RepositorioFicheroExcel#recuperarCodigosLabImq(java.lang.String)
	 */
	@Override
	public List<Correlacion> recuperarCodigosLabImq(String codigoLaboratorio) {
		try {
			leerFicheroExcel();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Correlacion> resultadoBusqueda = new ArrayList<Correlacion>();
		
		Sheet hojaTrabajoExel = libroTrabajoExcel.getSheetAt(0);
		
		Iterator<Row> rowIterator = hojaTrabajoExel.iterator();

		int contador = 0;
		int contadorCumpleCondiciones = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (contador > 0) {
				boolean cumpleCondiciones = false;
				Correlacion datosCorrelacion = new Correlacion();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getRowIndex() > 1) {
						int indiceColumna = cell.getColumnIndex();

						if (indiceColumna == 0) {
							
							if (cell.getNumericCellValue() == new Double(codigoLaboratorio).doubleValue()) {
								//System.out.println(cell.getNumericCellValue());
								cumpleCondiciones = true;
								//datosCorrelacion.setCodigoA( (new Double(cell.getNumericCellValue()).toString()));
								datosCorrelacion.setTipoA("LAB");
								datosCorrelacion.setTipoB("LAB");
								datosCorrelacion.setSistemaA("INFO33");
								
								if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOIMQ).doubleValue()) {
									datosCorrelacion.setSistemaB("MEDIKOSTA");
								}else if(cell.getNumericCellValue() == new Double(CODIGOLABORATORIOLAFITA).doubleValue()) {
									datosCorrelacion.setSistemaB("LAFITA");
								}else if(cell.getNumericCellValue() == new Double(CODIGOLABORATORIOAXPE).doubleValue()) {
									datosCorrelacion.setSistemaB("AXPE");
								}
								
							}																					
						}else if(indiceColumna == 1 && cumpleCondiciones) {
							datosCorrelacion.setCodigoB( cell.getStringCellValue());
						}else if(indiceColumna == 2 && cumpleCondiciones) {
							datosCorrelacion.setCodigoA((Integer.toString( (int)cell.getNumericCellValue())));
						}else if(indiceColumna == 5 && cumpleCondiciones) {
							String descripcionprueba = cell.getStringCellValue().replace('\'', ' ');
							datosCorrelacion.setDescripcion(descripcionprueba);
						}else if(indiceColumna == 6 && cumpleCondiciones) {
							if(cell.getStringCellValue().equalsIgnoreCase("D") || cell.getStringCellValue().equalsIgnoreCase("B")) {
								cumpleCondiciones = false;
							}
						}
					}					
				}
				if (cumpleCondiciones) {
					contadorCumpleCondiciones++;
					resultadoBusqueda.add(datosCorrelacion);					
				}
				
				
			}
			
			contador++;
		}
		
		System.out.println(contadorCumpleCondiciones);
		return resultadoBusqueda;
		
	}
	
	
	
	public String devolverDato() throws IOException {
		return this.ficheroExcel.getFD().toString();
	}

	@Override
	public List<Correlacion> recuperarCodigosCorrelaciones(String rutaFicheroExcel, String codigoLaboratorio) {
		// TODO Auto-generated method stub
		return null;
	}
	

}