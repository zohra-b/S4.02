package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exceptions.ConflictException;
import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.repositories.FruitRepository;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitService {
private final FruitRepository fruitRepo;

    @Autowired
    public FruitService(FruitRepository fruitRepo) {
        this.fruitRepo = fruitRepo;
    }

    public void addFruit(Fruit newFruit){
        validateFruit(newFruit);
        if (fruitRepo.existsByName(newFruit.getName())){
            throw new ConflictException("Fruit with name " + newFruit.getName() + " already exists");
        }
        fruitRepo.save(newFruit);
    }


    public List<Fruit> findAllFruits(){
        return fruitRepo.findAll();
    }

    public Fruit findFruitById(Integer id){
        return fruitRepo.findById(id).orElseThrow(() -> new RuntimeException("Fruit with id " + id + " not found"));
    }

    public Fruit updateFruit(Fruit fruit){
        return fruitRepo.save(fruit);

    }

    public void deleteFruitById(Integer id){
        Fruit fruit = findFruitById(id);
        fruitRepo.delete(fruit);
    }

    private void validateFruit(@NotNull Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }

        if (fruit.getQuantityKg() <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }
    }


