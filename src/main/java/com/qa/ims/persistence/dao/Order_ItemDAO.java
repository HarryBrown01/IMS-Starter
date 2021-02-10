package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order_Item;
import com.qa.ims.utils.DBUtils;

public class Order_ItemDAO implements Dao<Order_Item> {
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order_Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		return new Order_Item(id, order_id, item_id, quantity);
	}
	
	public String getPrice(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT SUM(I.price*OI.quantity) FROM orders_items OI JOIN items I ON OI.item_id=I.id WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				String sum = resultSet.getString(1);
				return sum;
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order_Item> readAll() {
		Long order_id;
		try (Scanner scanObject = new Scanner(System.in)) {
			System.out.println("Please enter first number: ");
			order_id = scanObject.nextLong();
		}
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			List<Order_Item> order_items = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					order_items.add(modelFromResultSet(resultSet));
				}
			}
			LOGGER.info("Total price for order " + order_id + " = £" + getPrice(order_id));
			return order_items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Order_Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order_Item read(Long id) {
		Long order_id;
		Long item_id;
		try (Scanner scanObject = new Scanner(System.in)) {
			System.out.println("Please enter order id: ");
			order_id = scanObject.nextLong();
			System.out.println("Please enter item id: ");
			item_id = scanObject.nextLong();
		}
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE order_id = ? AND item_id = ?");) {
			statement.setLong(1, order_id);
			statement.setLong(2, item_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order_Item create(Order_Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(order_id, item_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, t.getorder_id());
			statement.setLong(2, t.getitem_id());
			statement.setLong(3, t.getquantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order_Item update(Order_Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders_items SET order_id, item_id, quantity WHERE id = ?");) {
			statement.setLong(1, t.getorder_id());
			statement.setLong(2, t.getitem_id());
			statement.setLong(3, t.getquantity());
			statement.setLong(4, t.getId());
			statement.executeUpdate();
			return read(t.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		Long item_id;
		try (Scanner scanObject = new Scanner(System.in)) {
			System.out.println("Please enter item id: ");
			item_id = scanObject.nextLong();
		}
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE order_id = ? AND item_id = ?");) {
			statement.setLong(1, id);
			statement.setLong(2, item_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
