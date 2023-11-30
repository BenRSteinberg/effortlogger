package asuHelloWorldJavaFX;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StrongNormal {

	@Test
	public void SN1() {
		assertEquals("00:10", ASUHelloWorldJavaFX.formatTime(10000));
	}
	@Test
	public void SN2() {
		assertEquals("01:00", ASUHelloWorldJavaFX.formatTime(60000));
	}
	@Test
	public void SN3() {
		assertEquals("01:10", ASUHelloWorldJavaFX.formatTime(70000));
	}

}
