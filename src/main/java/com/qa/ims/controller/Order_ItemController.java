package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.Order_ItemDAO;
import com.qa.ims.persistence.domain.Order_Item;
import com.qa.ims.utils.Utils;

public class Order_ItemController implements CrudController<Order_Item> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private Order_ItemDAO order_itemDAO;
	private Utils utils;
	
	public Order_ItemController(Order_ItemDAO order_itemDAO, Utils utils) {
		super();
		this.order_itemDAO = order_itemDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order_Item> readAll() {
		List<Order_Item> order_items = order_itemDAO.readAll();
		for (Order_Item order_item : order_items) {
			LOGGER.info(order_item);
		}
		return order_items;
	}

	@Override
	public Order_Item create() {
		LOGGER.info("Please enter order ID:");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter item ID:");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter quantity:");
		Long quantity = utils.getLong();
		Order_Item order_item = order_itemDAO.create(new Order_Item(order_id, item_id, quantity));
		LOGGER.info("Item added to order");
		return order_item;
	}

	@Override
	public Order_Item update() {
		LOGGER.info("Please enter the id of the order item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter order ID");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter item ID:");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter quantity:");
		Long quantity = utils.getLong();
		Order_Item order_item = order_itemDAO.update(new Order_Item(id, order_id, item_id, quantity));
		LOGGER.info("Order Updated");
		return order_item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the Order you would like to delete an item from");
		Long id = utils.getLong();
		return order_itemDAO.delete(id);
	}

}
