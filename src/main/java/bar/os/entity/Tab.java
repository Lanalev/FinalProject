package bar.os.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Tab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tabId;
	private int tax;
	private Long total;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tabs_cocktail", 
	joinColumns = @JoinColumn(name = "tab_id"), 
	inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
	Set<Cocktail> cocktails = new HashSet<>();
	
}
