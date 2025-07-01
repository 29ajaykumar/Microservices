package com.currency.convarsion.service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.convarsion.service.entity.CurrencyConversion;
import com.currency.convarsion.service.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy; 
	
	// using rest templates, if 100 microservices , by this we need to repeat this code every where
	// that why spring cloud provide a framework that is Feign, it is called other microservices
	/*
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVaribale = new HashMap<>();
		uriVaribale.put("from", from);
		uriVaribale.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVaribale);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity, quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" Rest Template");
	}
	*/
	@Autowired
	RestTemplate rstTempalte;
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVaribale = new HashMap<>();
		uriVaribale.put("from", from);
		uriVaribale.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = rstTempalte.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVaribale);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity, quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" Rest Template");
	}
	// using rest templates
		@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
		public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
				@PathVariable BigDecimal quantity) {
			
			CurrencyConversion currencyConversion = proxy.retriveExchangeValue(from, to);
			return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
					quantity, quantity.multiply(currencyConversion.getConversionMultiple()),
					currencyConversion.getEnvironment() +" Feign");
		}
}
