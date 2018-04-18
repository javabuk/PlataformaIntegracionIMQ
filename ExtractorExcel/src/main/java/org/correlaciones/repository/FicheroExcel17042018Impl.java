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
import org.springframework.stereotype.Repository;

@Repository("FicheroExcel17042018Impl")
public class FicheroExcel17042018Impl implements RepositorioFicheroExcel {

	private FileOutputStream ficheroExcel;
	private Workbook libroTrabajoExcel;

	public static String CODIGOLABORATORIOIMQ = "1701";
	public static String CODIGOLABORATORIOAXPE = "1703";
	public static String CODIGOLABORATORIOLAFITA = "1704";

	public List<Correlacion> recuperarCodigosCorrelaciones(String rutaFicheroExcel, String codigoLaboratorio) {

		try {
			leerFicheroExcel(rutaFicheroExcel);
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

						if (indiceColumna == 0 ) {
							datosCorrelacion.setCodigoA((Integer.toString((int) cell.getNumericCellValue())));			
							cumpleCondiciones = true;
						}else if (indiceColumna == 3 && cumpleCondiciones) {

							if (cell.getNumericCellValue() == new Double(codigoLaboratorio).doubleValue()) {
								// System.out.println(cell.getNumericCellValue());
								// datosCorrelacion.setCodigoA( (new
								// Double(cell.getNumericCellValue()).toString()));
								datosCorrelacion.setTipoA("LAB");
								datosCorrelacion.setTipoB("LAB");
								datosCorrelacion.setSistemaA("INFO33");

								if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOIMQ).doubleValue()) {
									datosCorrelacion.setSistemaB("MEDIKOSTA");
								} else if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOLAFITA)
										.doubleValue()) {
									datosCorrelacion.setSistemaB("LAFITA");
								} else if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOAXPE)
										.doubleValue()) {
									datosCorrelacion.setSistemaB("AXPE");
								}

							}
						} else if (indiceColumna == 4 && cumpleCondiciones) {
							datosCorrelacion.setCodigoB(cell.getStringCellValue());
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
		return resultadoBusqueda;
	}
	
//	private Correlacion extraerCorrelacionesCelda(Cell cell, List<Correlacion> resultadoBusqueda, int contadorCumpleCondiciones, String codigoLaboratorio) {
//		boolean cumpleCondiciones = false;
//		Correlacion datosCorrelacion = new Correlacion();
//		if (cell.getRowIndex() == 1) {
//			int indiceColumna = cell.getColumnIndex();
//
//			if (indiceColumna == 0 ) {
//				datosCorrelacion.setCodigoA((Integer.toString((int) cell.getNumericCellValue())));			
//				cumpleCondiciones = true;
//			}else if (indiceColumna == 3 && cumpleCondiciones) {
//
//				if (cell.getNumericCellValue() == new Double(codigoLaboratorio).doubleValue()) {
//					// System.out.println(cell.getNumericCellValue());
//					// datosCorrelacion.setCodigoA( (new
//					// Double(cell.getNumericCellValue()).toString()));
//					datosCorrelacion.setTipoA("LAB");
//					datosCorrelacion.setTipoB("LAB");
//					datosCorrelacion.setSistemaA("INFO33");
//
//					if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOIMQ).doubleValue()) {
//						datosCorrelacion.setSistemaB("MEDIKOSTA");
//					} else if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOLAFITA)
//							.doubleValue()) {
//						datosCorrelacion.setSistemaB("LAFITA");
//					} else if (cell.getNumericCellValue() == new Double(CODIGOLABORATORIOAXPE)
//							.doubleValue()) {
//						datosCorrelacion.setSistemaB("AXPE");
//					}
//
//				}
//			} else if (indiceColumna == 4 && cumpleCondiciones) {
//				datosCorrelacion.setCodigoB(cell.getStringCellValue());
//			} 
//		}
//		if (cumpleCondiciones) {
//			contadorCumpleCondiciones++;
//			resultadoBusqueda.add(datosCorrelacion);
//		}
//	}


	private void leerFicheroExcel(String rutaFicheroExcel)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		libroTrabajoExcel = new XSSFWorkbook();
		libroTrabajoExcel = WorkbookFactory.create(new File(rutaFicheroExcel));
	}

	@Override
	public List<Correlacion> recuperarCodigosLabImq(String codigoLaboratorio) {
		// TODO Auto-generated method stub
		return null;
	}
}
