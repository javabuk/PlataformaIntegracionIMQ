package org.correlaciones.repository;

import java.util.List;

import org.correlaciones.model.CodigoCPT;

public interface INFO33Repositorio {

	List<CodigoCPT> consultaCPT(String codigoCPT);

}