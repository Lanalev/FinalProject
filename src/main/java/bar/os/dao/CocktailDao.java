package bar.os.dao;
//Author David Atwood

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.os.entity.BottleType;
import bar.os.entity.Cocktail;

public interface CocktailDao extends JpaRepository<Cocktail, Long> {

	Optional<Cocktail> findOpCocktailByName(String opCocktailName);

	Long findCocktailIdByName(String CocktailName);

	BottleType findBaseLiqourByCocktailId(Long cocktailId);


	Cocktail findByName(String cocktailName);







}
