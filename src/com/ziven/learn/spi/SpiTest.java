package com.ziven.learn.spi;

import java.util.ServiceLoader;

public class SpiTest {

	public static void main(String[] args) {
		ServiceLoader<SpiInterface> serviceLoader = ServiceLoader.load(SpiInterface.class);
		for (SpiInterface spi: serviceLoader){
			spi.execute();
		}
	}


}
