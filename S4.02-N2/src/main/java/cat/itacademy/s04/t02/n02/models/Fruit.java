package cat.itacademy.s04.t02.n02.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fruits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Fruit name can not be empty")
    private String name;

    @NotNull(message = "Quantity con not be null")
    @Min(value = 1, message = "Quantity must be minimum 1")
    private Integer quantityKg;
}
