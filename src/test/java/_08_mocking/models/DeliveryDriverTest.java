package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

	DeliveryDriver deliveryDriver;

	@Mock
	CellPhone cellPhone;

	@Mock
	Car car;

	@BeforeEach
	void setUp() {
        MockitoAnnotations.openMocks(this);

		deliveryDriver = new DeliveryDriver("Sameer", car, cellPhone);
	}

	@Test
	void itShouldWasteTime() {
		// given
		when(cellPhone.browseCatMemes()).thenReturn(true);
		// when
		boolean wasteTime = deliveryDriver.wasteTime();
		// then
		assertEquals(wasteTime, true);
	}

	@Test
	void itShouldRefuel() {
		// given
		int octaneGrade = 80;
		when(car.fillTank(octaneGrade)).thenReturn(true);
		// when
		boolean refueled = deliveryDriver.refuel(octaneGrade);
		// then
		assertEquals(refueled, true);
	}

	@Test
	void itShouldContactCustomer() {
		// given
		String phoneNumber = "000 000 0000";
		when(cellPhone.call(phoneNumber)).thenReturn(true);
		// when
		boolean actual = deliveryDriver.contactCustomer(phoneNumber);
		// then
		assertEquals(actual, true);
	}

}