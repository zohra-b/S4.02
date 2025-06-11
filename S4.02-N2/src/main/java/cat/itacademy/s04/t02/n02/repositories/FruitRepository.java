package cat.itacademy.s04.t02.n02.repositories;

import cat.itacademy.s04.t02.n02.models.Fruit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Integer> {


    boolean existsByName(String name);

}
