package com.healthycoderapp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {

	private String env = "dev";

	// Run exactly once before all other unit case
	@BeforeAll
	static void beforeAll() {
		// setting up db
		// starting server
		System.out.println("Starting and setting DB");

	}

	@AfterAll
	static void afterAll() {
		// Close up db
		// stop server
		System.out.println("Stop DB ans server");

	}

	@Nested
	class DietRecommended {
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
	}

	@Nested
	class CoderwithworstBMI {
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
	}

	@Nested
	class GetBMIScores {
		// test array equality
		@Test
		void shouldReturn_CorrectBMI_ScoreArray_WhenListNotempty() {

			// given
			List<Coder> coders = Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new Coder(1.82, 64.7));

			// when

			double[] coderwithWorstBMI = BMICalculator.getBMIScores(coders);
			double[] expected = { 18.52, 29.59, 19.53 };

			// then
			// this fails as it checks the location
			// assertEquals(expected, coderwithWorstBMI);

			assertArrayEquals(expected, coderwithWorstBMI, 0);
		}
	}

	@Nested
	class ParameterAndCsvSrc {
		// tParameterized test case (single value source)

		@ParameterizedTest
		@ValueSource(doubles = { 89.0, 95.0, 110.0 })
		void should_return_true_when_diet_recommended(Double coderweight) {
			// given
			double weight = coderweight;
			double height = 1.72;

			// when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			// then
			assertTrue(recommended);
		}

		// twwo value source
		@ParameterizedTest(name = "weight={0}, height={0}")
		@CsvSource(value = { "89.0,1.72", "95.0,1.75", "110.0,1.78" })
		void should_return_true_when_diet_recommended(Double coderweight, Double coderHeight) {
			// given
			double weight = coderweight;
			double height = coderHeight;

			// when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			// then
			assertTrue(recommended);
		}

		// using csv file
		// csv file
		@ParameterizedTest(name = "weight={0}, height={0}")
		@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
		void should_return_true_when_diet_recommended_usign_csv(Double coderweight, Double coderHeight) {
			// given
			double weight = coderweight;
			double height = coderHeight;

			// when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			// then
			assertTrue(recommended);
		}

	}

	@Nested
	class performance_and_assumption {
		// test performance should return in 1ms that has 1000 elements
		// if you want to run some test case only in prod
		// use assuemtrue
		@Test
		void shouldReturn_CorrectBMI_ScoreArray_WhenListNotempty_has_1000element_should_return_in_1_ms() {

			// given
			assumeTrue(BMICalculatorTest.this.env.equals("prod")); // assumptions
			List<Coder> coders = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}
			// when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
			// then
			assertTimeout(Duration.ofMillis(50), executable);
		}
	}
}
