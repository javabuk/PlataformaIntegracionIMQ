package org.correlaciones.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.correlaciones.model.CodigoCPT;
import org.correlaciones.model.CodigoGestor;
import org.correlaciones.model.Correlacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("gestorPeticionesRepositorioImpl")
public class GestorPeticionesRepositorioImpl {

	@Autowired()
	@Qualifier("DataSourceGestorPeticiones")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<CodigoGestor> buscarCodigo(String codigo, String sistema, String tipo) {
		return jdbcTemplate.query(
				  "SELECT * FROM PSI_CODIGOS WHERE CODIGO = ? AND IDSISTEMA = ? AND TIPO = ? ",
				  new Object[] { codigo, sistema, tipo },
				  (rs, rowNum) -> new CodigoGestor(rs.getString("CODIGO"), rs.getString("DESCRIPCION"), rs.getString("TIPO"), rs.getString("IDSISTEMA")
			));
	}
	
	public List<Correlacion> buscarCorrelacion(String codigoA, String sistemaA, String tipoA, String codigoB, String sistemaB, String tipoB) {
		return jdbcTemplate.query(
				  "SELECT * FROM PSI_CORRELACIONES WHERE CODIGOA = ? AND SISTEMAA = ? AND TIPOA = ? AND CODIGOB = ? AND SISTEMAB = ? AND TIPOB = ? ",
				  new Object[] { codigoA, sistemaA, tipoA, codigoB, sistemaB, tipoB },
				  (rs, rowNum) -> new Correlacion(rs.getString("CODIGOA"), rs.getString("SISTEMAA"), rs.getString("TIPOA"), rs.getString("CODIGOB"), rs.getString("SISTEMAB"), rs.getString("TIPOB"), "", ""
			));
	}
	

}
