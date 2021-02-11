package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class Order_ItemTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order_Item.class).verify();
	}
}
