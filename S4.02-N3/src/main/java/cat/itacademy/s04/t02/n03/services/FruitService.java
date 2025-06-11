package cat.itacademy.s04.t02.n03.services;

import cat.itacademy.s04.t02.n03.exceptions.ConflictException;
import cat.itacademy.s04.t02.n03.models.Fruit;
import cat.itacademy.s04.t02.n03.repositories.FruitRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FruitService {
    private final FruitRepository fruitRepo;

    @Autowired
    public FruitService(FruitRepository fruitRepo) {
        this.fruitRepo = fruitRepo;
    }


    public void addFruit(Fruit newFruit){
        validateFruit(newFruit);
        if (fruitRepo.existsByNameIgnoreCase(newFruit.getName())){
            throw new ConflictException("Fruit with name " + newFruit.getName() + " already exists");
        }
        fruitRepo.save(newFruit);
    }

    public List<Fruit> findAllFruits(){
        return fruitRepo.findAll();
    }

    public Fruit findFruitById(String id){
        return fruitRepo.findById(id).orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Fruit with id " + id + " not found"));
    }

    public Fruit updateFruit(String id, Fruit fruitUpdate){
        Fruit existingFruit = findFruitById(id);


        if (fruitUpdate.getName() != null && !fruitUpdate.getName().equalsIgnoreCase(existingFruit.getName())){
            existingFruit.setName(fruitUpdate.getName());
        }
        if (fruitUpdate.getQuantityKg() != null && fruitUpdate.getQuantityKg() != existingFruit.getQuantityKg()){
            existingFruit.setQuantityKg(fruitUpdate.getQuantityKg());
        }
        return fruitRepo.save(existingFruit);


    }

    public void deleteFruitById(String id){
        Fruit fruit = findFruitById(id);
        fruitRepo.delete(fruit);
    }

    private void validateFruit(Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }

        if (fruit.getQuantityKg() <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
    }


}
