package model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
public class ListDetails {
	@Id
	@GeneratedValue
	private int id;
	private String listName;
	private LocalDate tripDate;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Shopper shopper;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<ListItem> listOfItems;
	
	public ListDetails() {
    }
	
	// Constructor with id parameter
    public ListDetails(int id, String listName, LocalDate tripDate, Shopper shopper, List<ListItem> listOfItems) {
        this.id = id;
        this.listName = listName;
        this.tripDate = tripDate;
        this.shopper = shopper;
        this.listOfItems = listOfItems;
    }

    // Constructor without id parameter
    public ListDetails(String listName, LocalDate tripDate, Shopper shopper, List<ListItem> listOfItems) {
        this.listName = listName;
        this.tripDate = tripDate;
        this.shopper = shopper;
        this.listOfItems = listOfItems;
    }

    // Constructor without listOfItems parameter
    public ListDetails(String listName, LocalDate tripDate, Shopper shopper) {
        this.listName = listName;
        this.tripDate = tripDate;
        this.shopper = shopper;
        // Assuming listOfItems is initialized as an empty list
        this.listOfItems = List.of(); // Available in Java 9 and later, or use new ArrayList<>() for earlier versions
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public LocalDate getTripDate() {
		return tripDate;
	}
	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
	public List<ListItem> getListOfItems() {
		return listOfItems;
	}
	public void setListOfItems(List<ListItem> listOfItems) {
		this.listOfItems = listOfItems;
	}
}