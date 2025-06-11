package cat.itacademy.s04.t02.n03.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fruits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    @Id
    private String id; // mongo genere des ObjectId sous forme de string
    private String name;
    private Integer quantityKg;
}
