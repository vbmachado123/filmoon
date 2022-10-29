package br.com.tevitto.filmoon.repository;

import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    Optional<Item> findByTitle(String title);

    Optional<Item> findByType(Type type);
}
