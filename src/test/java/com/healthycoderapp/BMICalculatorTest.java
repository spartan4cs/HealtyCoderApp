package com.healthycoderapp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {

	
	//Run exactly once before all other unit case
	@BeforeAll 
	static void beforeAll(){
		//setting up db 
		//starting server
		System.out.println("Starting and setting DB");
		
	}
	
	@AfterAll 
	static void afterAll(){
		//Close up db 
		//stop server
		System.out.println("Stop DB ans server");
		
	}
	
	// test true value
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

	// test false value

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

	// test exception

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

	// test multiple assert
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

	// test null value

	@Test
	void shouldReturnNull_Coder_With_WorstBMI() {

		// given
		List<Coder> coders = new ArrayList<>();// Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new
												// Coder(1.82, 64.7));

		// when

		Coder coderwithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then

		assertNull(coderwithWorstBMI);

	}

	// test array equality
	@Test
	void shouldReturn_CorrectBMI_ScoreArray_WhenListNotempty() {

		// given
		List<Coder> coders = Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new Coder(1.82, 64.7));

		// when

		double[] coderwithWorstBMI = BMICalculator.getBMIScores(coders);
		double[] expected = { 18.52, 29.59, 19.53 };

		// then
		 //this fails as it checks the location
		//assertEquals(expected, coderwithWorstBMI);
		
		assertArrayEquals(expected, coderwithWorstBMI,0);
	}
}
