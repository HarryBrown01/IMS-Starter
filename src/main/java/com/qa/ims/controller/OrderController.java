package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Order_Item;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		LOGGER.info("Create ORDER or add ITEM to order");
		String answer = utils.getString().toLowerCase();
		switch (answer) {
		case "order":
			List<Order> orders = orderDAO.readAll();
			for (Order order : orders) {
				LOGGER.info(order);
			}
			return orders;
		case "item":
			readAllItems();
		default:
			return null;
		}
	}
	public List<Order_Item> readAllItems() {
		LOGGER.info("Please enter order ID:");
		Long order_id = utils.getLong();
		List<Order_Item> order_items = orderDAO.readAllItems(order_id);
		for (Order_Item order_item : order_items) {
			LOGGER.info(order_item);
		}
		return order_items;
	}

	@Override
	public Order create() {
		LOGGER.info("Create ORDER or add ITEM to order");
		String answer = utils.getString().toLowerCase();
		switch (answer) {
		case "order":
			LOGGER.info("Please enter customer ID:");
			Long customer_id = utils.getLong();
			Order order = orderDAO.create(new Order(customer_id));
			LOGGER.info("Order created");
			return order;
		case "item":
			LOGGER.info("Please enter order ID:");
			Long order_id = utils.getLong();
			LOGGER.info("Please enter item ID:");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter quantity:");
			Long quantity = utils.getLong();
			Order orderItem = (Order) orderDAO.createOrderItem(new Order_Item(order_id, item_id, quantity));
			LOGGER.info("Item added");
			return orderItem;
		default:
			return null;
		}
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter customer ID");
		Long customer_id = utils.getLong();
		Order order = orderDAO.update(new Order(id, customer_id));
		LOGGER.info("Order Updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Delete order or item information");
		String answer = utils.getString().toLowerCase();
		switch (answer) {
		case "order":
			LOGGER.info("Please enter the id of the Order you would like to delete");
			Long order_id = utils.getLong();
			return orderDAO.delete(order_id);
		case "item":
			LOGGER.info("Please enter the id of the Order you would like to delete the item from");
			Long order_id2 = utils.getLong();
			LOGGER.info("Please enter the id of the Order you would like to delete the item from");
			Long item_id = utils.getLong();
			return orderDAO.deleteOrderItem(order_id2, item_id);
		default:
			return 0;
		}
	}
}
