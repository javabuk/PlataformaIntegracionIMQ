package org.correlaciones.model;

public class Correlacion {

	private String codigoA;
	private String codigoB;
	private String descripcion;
	private String sistemaA;
	private String sistemaB;
	private String tipoA;
	private String tipoB;
	
	
	
	public Correlacion() {
		super();		
	}

	public Correlacion(String codigoA, String sistemaA, String tipoA, String codigoB, String sistemaB, String tipoB) {
		super();
		this.codigoA = codigoA;
		this.sistemaA = sistemaA;
		this.tipoA = tipoA;
		this.codigoB = codigoB;
		this.sistemaB = sistemaB;
		this.tipoB = tipoB;
	}
	
	public String getCodigoA() {
		return codigoA;
	}
	
	public String getCodigoB() {
		return codigoB;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getSistemaA() {
		return sistemaA;
	}
	
	public String getSistemaB() {
		return sistemaB;
	}
	
	public String getTipoA() {
		return tipoA;
	}
	
	public String getTipoB() {
		return tipoB;
	}
	
	public void setCodigoA(String codigoA) {
		this.codigoA = codigoA;
	}
	
	public void setCodigoB(String codigoB) {
		this.codigoB = codigoB;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setSistemaA(String sistemaA) {
		this.sistemaA = sistemaA;
	}
	
	public void setSistemaB(String sistemaB) {
		this.sistemaB = sistemaB;
	}

	public void setTipoA(String tipoA) {
		this.tipoA = tipoA;
	}

	public void setTipoB(String tipoB) {
		this.tipoB = tipoB;
	}
	
	
	
}
