package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Collections;

class MyDonutShopTest {

	MyDonutShop myDonutShop;

	@Mock
	PaymentService paymentService;

	@Mock
	DeliveryService deliveryService;

	@Mock
	BakeryService bakeryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
	}

	@Test
	void itShouldTakeDeliveryOrder() throws Exception {

		myDonutShop.openForTheDay();
		// given
		Order order = new Order("name", "number", 1, 10d, "credit card", true);
		// when
		when(paymentService.charge(order)).thenReturn(true);

		// then
		assertThrows(IllegalArgumentException.class, () -> myDonutShop.takeOrder(order));
		verify(bakeryService, never()).removeDonuts(1);
	}

	@Test
	void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {

		myDonutShop.openForTheDay();
		// given
		Order order = new Order("name", "number", 1, 10d, "credit card", true);
		// when
		when(paymentService.charge(order)).thenReturn(true);

		// then
		Throwable throwed = assertThrows(IllegalArgumentException.class, () -> myDonutShop.takeOrder(order));
		verify(bakeryService, never()).removeDonuts(1);
	}

	@Test
	void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException() {
		Order order = new Order("name", "number", 1, 10d, "credit card", true);

		// then
		Throwable throwed = assertThrows(IllegalStateException.class, () -> myDonutShop.takeOrder(order));
		verify(bakeryService, never()).removeDonuts(1);
	}

}