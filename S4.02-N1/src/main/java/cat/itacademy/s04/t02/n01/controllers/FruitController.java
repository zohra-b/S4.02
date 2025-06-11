package cat.itacademy.s04.t02.n01.controllers;

import cat.itacademy.s04.t02.n01.models.Fruit;
import cat.itacademy.s04.t02.n01.services.FruitService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private final FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService){
        this.fruitService = fruitService;

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFruits(){
        if (fruitService.findAllFruits().isEmpty()){
            return new ResponseEntity<>("There is no fruit in your list", HttpStatus.OK);
        } else return new ResponseEntity<>(fruitService.findAllFruits(), HttpStatus.OK);
    }



    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Integer id){
        Fruit fruit = fruitService.findFruitById(id);
        return ResponseEntity.ok(fruit);
    }


    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit newFruit) {
        System.out.println("Received fruit: " + newFruit);
        if (newFruit != null) {
            System.out.println("Name: " + newFruit.getName());
            System.out.println("Quantity: " + newFruit.getQuantityKg());
        } else {
            System.out.println("newFruit is NULL!");
        }

        fruitService.addFruit(newFruit);
        return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruit(@PathVariable Integer id, @RequestBody Fruit updatedFruit){
        try {
            Fruit fruit = fruitService.findFruitById(id);
            System.out.println("Received update for ID " + id + ": " + updatedFruit);
            if (updatedFruit.getName() != null){
                fruit.setName(updatedFruit.getName());
            }
            if (updatedFruit.getQuantityKg() != null){
                fruit.setQuantityKg(updatedFruit.getQuantityKg());
            }
            fruitService.updateFruit(fruit);
            return ResponseEntity.ok(fruit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur : " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruit(@PathVariable Integer id){
        if(fruitService.findFruitById(id) == null){
            return new ResponseEntity("The fruit with id " + id + " does not exist in database", HttpStatus.NOT_FOUND);
        }

        fruitService.deleteFruitById(id);
        return new ResponseEntity<>("Fruit successfully deleted", HttpStatus.OK);



    }
}
