package cat.itacademy.s04.t02.n01.repositories;

import cat.itacademy.s04.t02.n01.models.Fruit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Integer> {
}
// <Fruit, Integer> : la classe que va gerer le service (doit Etre annotee @Entity) et le type de clé primaire
// LIEN AVEC LA BASE DE DONNEES
//C’est une interface qui hérite de JpaRepository, ce qui donne automatiquement :
// findAll()
// findById()
// save()
// deleteById()