package com.mercadolibre.desafiofinaljosejimenez.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.SampleDTO;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomSampleBean {
	private Random random = new Random();

	public SampleDTO random() {
		return new SampleDTO(random.nextInt(Integer.MAX_VALUE));
	}
}

