package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class DietPlannerTest {

	private DietPlanner dietPlanner;

	// THIS WILL BE INVOKED AT EACH DIET PLANNER TEST CASE
	@BeforeEach
	void setUp() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}

	@AfterEach
	void afterEach() {
		System.out.println("a unit test was finished");
	}

	//@Test
	@RepeatedTest(value = 10,name = RepeatedTest.LONG_DISPLAY_NAME)
	void should_return_correct_dietplanner_when_correct_coder() {

		// given

		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);
		// when
		DietPlan actual = dietPlanner.calculateDiet(coder);

		// then
		// will not work as it ches the mem location
		//assertEquals(expected, actual);

		assertAll(() -> assertEquals(expected.getCalories(), actual.getCalories()),
				() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
				()->assertEquals(expected.getFat(),actual.getFat()),
				()->assertEquals(expected.getProtein(),actual.getProtein())
				);

	}

}
