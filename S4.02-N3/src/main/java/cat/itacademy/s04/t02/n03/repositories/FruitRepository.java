package cat.itacademy.s04.t02.n03.repositories;

import cat.itacademy.s04.t02.n03.models.Fruit;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FruitRepository extends MongoRepository<Fruit, String> {
    public boolean existsByNameIgnoreCase(String name);
}
