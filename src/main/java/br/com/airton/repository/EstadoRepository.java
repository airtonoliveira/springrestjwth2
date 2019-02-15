package br.com.airton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.airton.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	

}
