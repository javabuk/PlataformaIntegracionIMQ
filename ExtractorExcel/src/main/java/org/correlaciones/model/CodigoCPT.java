package org.correlaciones.model;

public class CodigoCPT {

	
	private Integer id;
	private String codCPT;
	private String Descripcion;
	
	public CodigoCPT(Integer id, String codCPT, String descripcion) {
		super();
		this.id = id;
		this.codCPT = codCPT;
		Descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodCPT() {
		return codCPT;
	}

	public void setCodCPT(String codCPT) {
		this.codCPT = codCPT;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "CodigosCPT [id=" + id + ", codCPT=" + codCPT + ", Descripcion=" + Descripcion + "]";
	}
	
	
	
}
