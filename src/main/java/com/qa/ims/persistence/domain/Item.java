package com.qa.ims.persistence.domain;

public class Item {
	
	private Long id;
	private String Name;
	private Double Price;

	public Item(String Name, Double Price) {
		this.setName(Name);
		this.setPrice(Price);
	}

	public Item(Long id, String Name, Double Price) {
		this.setId(id);
		this.setName(Name);
		this.setPrice(Price);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(Double Price) {
		this.Price = Price;
	}
	
	@Override
	public String toString() {
		return "id:" + id + " name:" + Name + " price: £" + Price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((Price == null) ? 0 : Price.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Price == null) {
			if (other.Price != null)
				return false;
		} else if (!Price.equals(other.Price))
			return false;
		return true;
	}
}
