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
        List<Fruit> fruits = fruitService.findAllFruits();
        if (fruits.isEmpty()) {
            return ResponseEntity.ok("There is no fruit in your list");
        }
        return ResponseEntity.ok(fruitService.findAllFruits());
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Integer id){
        return ResponseEntity.ok(fruitService.findFruitById(id));
    }


    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit newFruit) {
        return new ResponseEntity<>(fruitService.addFruit(newFruit), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable Integer id, @RequestBody Fruit updatedFruit){
            return ResponseEntity.ok( fruitService.updateFruit(id, updatedFruit));
      }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable Integer id){
        fruitService.deleteFruitById(id);
        return ResponseEntity.ok("Fruit successfully deleted");



    }
}
