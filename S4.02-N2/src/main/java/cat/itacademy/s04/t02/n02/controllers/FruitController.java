package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.services.FruitService;

import java.util.HashMap;
import java.util.Map;

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
        fruitService.addFruit(newFruit);
        return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruit(@PathVariable Integer id, @RequestBody Fruit fruitUpdate){
        Fruit fruit = fruitService.findFruitById(id);

        if (fruitUpdate.getName() != null){
            fruit.setName(fruitUpdate.getName());
        }
        if (fruitUpdate.getQuantityKg() != null){
            fruit.setQuantityKg(fruitUpdate.getQuantityKg());
        }
        Fruit updatedFruit = fruitService.updateFruit(fruit);
        Map<String, Fruit> response = new HashMap<>();
        response.put("Fruit successfully updated", updatedFruit);

        return new ResponseEntity(response,  HttpStatus.CREATED );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFruit(@PathVariable int id){
        fruitService.deleteFruitById(id);
        return new ResponseEntity("Fruit deleted", HttpStatus.OK);
    }
}
