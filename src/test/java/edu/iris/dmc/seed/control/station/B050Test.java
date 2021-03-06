package edu.iris.dmc.seed.control.station;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;

import edu.iris.dmc.seed.BTime;
import edu.iris.dmc.seed.Blockette;
import edu.iris.dmc.seed.BlocketteFactory;
import edu.iris.dmc.seed.SeedException;

public class B050Test {

	String text = "0500154I58H1+28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NIM";
	             //05001555I58H1+28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NIM
	@Test
	public void fromText() throws Exception {
		Blockette b050 = BlocketteFactory.create(text.getBytes());
		assertEquals(text, b050.toSeedString());
	}
	
	@Test
	public void networkCode() throws Exception {
		String text = "0500154I58H1+28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NI ";
		   
		B050 b050 = (B050) BlocketteFactory.create(text.getBytes());
		assertEquals(text, b050.toSeedString());
		assertEquals("I", b050.getNetworkCode());
	}

	@Test
	public void fromObject1() throws Exception {
		B050 b = new B050();
		b.setNetworkCode("IM");
		b.setStationCode("I58H1");

		b.setSiteName("Midway Islands Infrasonic Array, Site I58H1, USA");
		b.setStartTime(BTime.valueOf(2013, 315, 0, 0, 0, 0));
		b.setEndTime(BTime.valueOf(2999, 365, 23, 59, 59, 0));
		b.setLatitude(28.209718);
		b.setLongitude(-177.381430);
		b.setElevation(4.6);
		b.setBit32BitOrder(3210);
		b.setBit16BitOrder(10);
		b.setUpdateFlag('N');
		assertEquals(text, b.toSeedString());
	}

	@Test
	public void fromObject2() throws Exception {
		B050 b = new B050();
		b.setNetworkCode("IM");
		b.setStationCode("I58H");

		b.setSiteName("Midway Islands Infrasonic Array, Site I58H1, USA");
		b.setStartTime(BTime.valueOf(2013, 315, 0, 0, 0, 0));
		b.setEndTime(BTime.valueOf(2999, 365, 23, 59, 59, 0));
		b.setLatitude(28.209718);
		b.setLongitude(-177.381430);
		b.setElevation(4.6);
		b.setBit32BitOrder(3210);
		b.setBit16BitOrder(10);
		b.setUpdateFlag('N');

		assertEquals("0500154I58H +28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NIM", b.toSeedString());
	}
	
	@Test
	public void fromObject3() throws Exception {
		Blockette b050 = BlocketteFactory.create("0500154 I58H+28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NIM".getBytes());
		assertEquals("0500154I58H +28.209718-177.381430+0004.60000000Midway Islands Infrasonic Array, Site I58H1, USA~0003210102013,315,00:00:00.0000~2999,365,23:59:59.0000~NIM", b050.toSeedString());
	}
	
	@Test
	public void exceptionHandling() throws Exception {	
	    Assertions.assertThrows(SeedException.class, () -> {
		B050 b = new B050();
		b.setNetworkCode("IM");
		b.setStationCode("I58H111");

		b.setSiteName("Midway Islands Infrasonic Array, Site I58H1, USA");
		b.setStartTime(BTime.valueOf(2013, 315, 0, 0, 0, 0));
		b.setEndTime(BTime.valueOf(2999, 365, 23, 59, 59, 0));
		b.setLatitude(28.209718);
		b.setLongitude(-177.381430);
		b.setElevation(4.6);
		b.setBit32BitOrder(3210);
		b.setBit16BitOrder(10);
		b.setUpdateFlag('N');
		b.toSeedString();
	    });
	}
	
	
}
