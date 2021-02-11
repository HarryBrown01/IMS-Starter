INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('harry', 'brown');

INSERT INTO `items` (`name`, `price`) VALUES ('Shoes', '12.99');
INSERT INTO `items` (`name`, `price`) VALUES ('Gloves', '10.99');

INSERT INTO `orders` (`customer_id`) VALUES ('1');
INSERT INTO `orders` (`customer_id`) VALUES ('2');

INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES ('1', '1', '2');
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES ('2', '1', '1');
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES ('2', '2', '1');