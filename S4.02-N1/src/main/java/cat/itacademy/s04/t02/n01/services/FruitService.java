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

    public Fruit addFruit(Fruit newFruit) {
        System.out.println("Received fruit: " + newFruit);
        if (newFruit == null || newFruit.getName() == null || newFruit.getQuantityKg() == null) {
            throw new IllegalArgumentException("Fruit name and quantity must not be null");
        }
        return fruitRepository.save(newFruit);
    }


    public List<Fruit> findAllFruits() {
        return fruitRepository.findAll();
    }


    public Fruit findFruitById(Integer id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Fruit with id " + id + "not found"));
    }

    public Fruit updateFruit(Integer id, Fruit updatedFruit) {
        Fruit existingFruit = findFruitById(id);
        System.out.println("Received update for Id " + id + ": " + updatedFruit);
        if (updatedFruit.getName() != null){
            existingFruit.setName(updatedFruit.getName());
        }
        if (updatedFruit.getQuantityKg() != null){
            existingFruit.setQuantityKg(updatedFruit.getQuantityKg());
        }
        return fruitRepository.save(existingFruit);
    }


public void deleteFruitById(Integer id) {
    Fruit fruit = findFruitById(id);
    fruitRepository.delete(fruit);
}

}
