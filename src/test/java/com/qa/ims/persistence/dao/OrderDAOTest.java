package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Order_Item;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(3L, 1L);
		assertEquals(created, DAO.create(created));
	}
	@Test
	public void testCreateItem() {
		final Order_Item created = new Order_Item(1L, 2L, 3L);
		assertEquals(null, DAO.createOrderItem(created));
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L));
		expected.add(new Order(2L, 2L));
		assertEquals(expected, DAO.readAll());
	}
	@Test
	public void testReadAllItems() {
		List<Order_Item> expected = new ArrayList<>();
		expected.add(new Order_Item(1L, 1L, 1L, 2L));
		Long order_id = (long) 1;
		assertEquals(expected, DAO.readAllItems(order_id));
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(2L, 2L), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L), DAO.read(ID));
	}
	
	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 2L);
		assertEquals(null, DAO.update(updated));
	}
	
	@Test
	public void testDeleteItem() {
		assertEquals(1, DAO.deleteOrderItem(2L, 1L));
	}
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L));
	}
}
