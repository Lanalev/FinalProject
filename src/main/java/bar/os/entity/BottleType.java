package bar.os.entity;
//Author David Atwood and Svitlana Leven

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// Author: David Atwood 
//
//

@Data
@Entity
public class BottleType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bottleTypeId;
	
	
	@Column(unique = true)
	private String name;
	
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "baseLiqour", cascade = CascadeType.ALL)
	private Set<Cocktail> cocktails = new HashSet<>();
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "bottleType", cascade = CascadeType.ALL)
	private Set<Inventory> inventory = new HashSet<>();
	
}
