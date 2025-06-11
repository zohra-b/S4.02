package cat.itacademy.s04.t02.n03.controllers;

import cat.itacademy.s04.t02.n03.models.Fruit;
import cat.itacademy.s04.t02.n03.services.FruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    private final FruitService fruitService;

@Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFruits() {
        if (fruitService.findAllFruits().isEmpty()) {
            return new ResponseEntity<>("There is no fruit in your list", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(fruitService.findAllFruits(), HttpStatus.OK);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity getOneFruitById(@PathVariable String id) {
        if (fruitService.findFruitById(id) == null) {
            return new ResponseEntity("The fruit with id " + id + "does not exist in database", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(fruitService.findFruitById(id), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewFruit(@RequestBody  Fruit newFruit){
        if (newFruit == null ){
            return new ResponseEntity<>("New fruit is null", HttpStatus.NO_CONTENT);
        } else {
            fruitService.addFruit(newFruit);
            return new ResponseEntity<>(newFruit, HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruit(
            @RequestBody Fruit fruitUpdate,
            @PathVariable String id){

        Fruit updatedFruit = fruitService.updateFruit(id, fruitUpdate);
        return ResponseEntity.ok("Fruit succesfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruit(@PathVariable String id){
        if(fruitService.findFruitById(id) == null){
            return new ResponseEntity("The fruit with id " + id + " does not exist in database", HttpStatus.NOT_FOUND);
        }

        fruitService.deleteFruitById(id);
        return new ResponseEntity<>("Fruit successfully deleted", HttpStatus.OK);


    }


}
