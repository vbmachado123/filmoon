package br.com.tevitto.filmoon.repository;

import br.com.tevitto.filmoon.data.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeRepository extends JpaRepository<Type, Long>, JpaSpecificationExecutor<Type> {
}
