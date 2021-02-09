package com.qa.ims.persistence.domain;

public class Order_Item {
	private Long id;
	private Long order_id;
	private Long item_id;
	private Long quantity;

	public Order_Item(Long order_id, Long item_id, Long quantity) {
		this.setorder_id(order_id);
		this.setitem_id(item_id);
		this.setquantity(quantity);
	}

	public Order_Item(Long id, Long order_id, Long item_id, Long quantity) {
		this.setId(id);
		this.setorder_id(order_id);
		this.setitem_id(item_id);
		this.setquantity(quantity);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getorder_id() {
		return order_id;
	}

	public void setorder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	public Long getitem_id() {
		return item_id;
	}

	public void setitem_id(Long item_id) {
		this.item_id = item_id;
	}
	
	public Long getquantity() {
		return quantity;
	}

	public void setquantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "id:" + id + " order_id:" + order_id + " item_id:" + item_id + " quantity:" + quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		Order_Item other = (Order_Item) obj;
		if (getorder_id() == null) {
			if (other.getorder_id() != null)
				return false;
		} else if (!getorder_id().equals(other.getorder_id()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
}
