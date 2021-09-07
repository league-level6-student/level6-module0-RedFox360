package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

	Payroll payroll = new Payroll();

	@Test
	void itShouldCalculatePaycheck() {
		// given
		Payroll roll = new Payroll();
		double response = roll.calculatePaycheck((double) 20, 6);
		// when
		double expected = 120.0;
		// then

		assertEquals(response, expected);
	}

	@Test
	void itShouldCalculateMileageReimbursement() {
		// given
		Payroll roll = new Payroll();
		// when
		double response = roll.calculateMileageReimbursement(5);
		double expected = 5 * 0.575;
		// then
		assertEquals(response, expected);
	}

	@Test
	void itShouldCreateOfferLetter() {
		// given
		Payroll roll = new Payroll();
		// when
		String response = roll.createOfferLetter("Sameer", 20.0);
		String expected = "Hello Sameer, We are pleased to offer you an hourly wage of 20.0";
		// then
		
		assertEquals(response, expected);
	}

}