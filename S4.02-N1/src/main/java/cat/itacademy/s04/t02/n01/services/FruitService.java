package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.models.Fruit;
import cat.itacademy.s04.t02.n01.repositories.FruitRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    @Autowired
    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Fruit addFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public List<Fruit> findAllFruits() {
        return fruitRepository.findAll();
    }

    public Fruit findFruitById(Integer id) {
        return fruitRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Fruit with id " + id + "not found"));
    }

    public Fruit updateFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public void deleteFruitById(Integer id) {
        Fruit fruit = findFruitById(id);
        fruitRepository.delete(fruit);
    }


}
