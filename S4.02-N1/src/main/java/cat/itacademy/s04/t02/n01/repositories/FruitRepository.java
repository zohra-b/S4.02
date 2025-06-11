package cat.itacademy.s04.t02.n01.repositories;

import cat.itacademy.s04.t02.n01.models.Fruit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Integer> {
}