package br.com.tevitto.filmoon.repository;

import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RentRepository extends JpaRepository<Rent, Long>, JpaSpecificationExecutor<Rent> {
}
