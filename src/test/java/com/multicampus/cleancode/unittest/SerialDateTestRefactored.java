package com.multicampus.cleancode.unittest;

public class SerialDateTestRefactored {
	
	void _5월31일에_한달을_더하면_6월30일() {
		SerialDate _5월31일 = SerialDate.createInstance(31, 5, 2004);
		SerialDate _6월30일 = SerialDate.createInstance(30, 6, 2004);
		
		SerialDate actual = SerialDate.addMonths(1, _5월31일);
		
		assertTrue(actual.isEqualTo(_6월30일));
	}
}
