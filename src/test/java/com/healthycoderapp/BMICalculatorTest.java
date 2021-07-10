package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

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

	@Test
	void should_return_false_when_diet_recommended() {
		// given
		double weight = 50.0;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		// then
		assertFalse(recommended);
	}

	@Test
	void should_ThrowExceptionWhenHeight_zero() {
		// given
		double weight = 50.0;
		double height = 0.0;

		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
		// then
		assertThrows(ArithmeticException.class, executable);

	}

	@Test
	void shouldReturnCoder_With_WorstBMI_WhenListNotempty() {

		// given
		List<Coder> coders = Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new Coder(1.82, 64.7));

		// when

		Coder coderwithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then

		assertAll(() -> assertEquals(1.82, coderwithWorstBMI.getHeight()),
				() -> assertEquals(98.0, coderwithWorstBMI.getWeight()));

	}
}
