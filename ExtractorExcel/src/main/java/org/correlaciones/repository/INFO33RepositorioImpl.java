package org.correlaciones.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.correlaciones.model.CodigoCPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("info33RepositorioImpl")
public class INFO33RepositorioImpl implements INFO33Repositorio {
	
	@Autowired()
	@Qualifier("DataSourceINFO") 
	  private DataSource dataSource;
	  private JdbcTemplate jdbcTemplate;

	  @PostConstruct
	  private void postConstruct() {
	      jdbcTemplate = new JdbcTemplate(dataSource);
	  }
	  
	  /* (non-Javadoc)
	 * @see org.correlaciones.repository.INFO33Repositorio#consultaCPT(java.lang.String)
	 */
	@Override
	public List<CodigoCPT> consultaCPT(String codigoCPT){
		  //String sentenciaSQL = String.format( "SELECT * FROM I33_HC_MAESTRO_CPT CPT WHERE CPT.CODCPT = '%s'", codigoCPT);
		  
		  return jdbcTemplate.query(
				  "SELECT * FROM I33_HC_MAESTRO_CPT CPT WHERE CPT.CODCPT = ?",
				  new Object[] { codigoCPT },
				  (rs, rowNum) -> new CodigoCPT(rs.getInt("ID"), rs.getString("CODCPT"), rs.getString("DESCRIPCION")
				  ));
	  }
}
