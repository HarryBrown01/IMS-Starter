package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Order_Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() {
		final Long customer_id = 2L;
		final String answer = "order";
		final Order created = new Order(customer_id);

		Mockito.when(utils.getString()).thenReturn(answer);
		Mockito.when(utils.getLong()).thenReturn(customer_id);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		final String answer = "order";
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L));
		orders.add(new Order(2L));

		Mockito.when(utils.getString()).thenReturn(answer);
		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	@Test
	public void testReadAllItems() {
		final Long order_id = 1L;
		List<Order_Item> order_items = new ArrayList<>();
		order_items.add(new Order_Item(1L, 1L, 2L));

		Mockito.when(utils.getLong()).thenReturn(order_id);
		Mockito.when(dao.readAllItems(order_id)).thenReturn(order_items);

		assertEquals(order_items, controller.readAllItems());

		Mockito.verify(dao, Mockito.times(1)).readAllItems(order_id);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;
		final String answer = "order";

		Mockito.when(utils.getString()).thenReturn(answer);
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
}