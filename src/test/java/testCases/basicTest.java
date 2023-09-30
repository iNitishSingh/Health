package testCases;

import org.testng.annotations.Test;

public class basicTest {

	@Test
	public void firsttest() {
		System.out.println("This is first test");
		System.out.println(System.getProperty("brow"));
	}
	@Test
	public void secondtest() {
		System.out.println("This is second test");
	}
	@Test
	public void thirdtest() {
		System.out.println("This is third test");
	}
}
