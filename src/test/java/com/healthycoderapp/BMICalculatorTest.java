package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {

	@Test
	void should_return_true_when_diet_recommended() {
		// given
		double weight = 89.0;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		// then
		assertTrue(recommended);
	}

	void should_return_false_when_diet_recommended() {
		// given
		double weight = 50.0;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		// then
		assertFalse(recommended);
	}

	void should_ThrowExceptionWhenHeight_zero() {
		// given
		double weight = 50.0;
		double height = 0.0;

		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
		// then
		assertThrows(ArithmeticException.class, executable);

	}
}
