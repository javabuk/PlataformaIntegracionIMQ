package org.correlaciones.model;

public class CodigoGestor {

	
	private String codigo;
	private String descripcion;
	private String tipo;
	private String sistema;
	
	public CodigoGestor(String codigo, String descripcion, String tipo, String sistema) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.sistema = sistema;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	@Override
	public String toString() {
		return "CodigoGestor [codigo=" + codigo + ", descripcion=" + descripcion + ", tipo=" + tipo + ", sistema="
				+ sistema + "]";
	}
	
}
