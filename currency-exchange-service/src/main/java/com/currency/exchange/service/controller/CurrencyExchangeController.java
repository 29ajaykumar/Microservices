package com.currency.exchange.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.exchange.service.controller.dto.CurrencyExchange;
import com.currency.exchange.service.reository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	Environment environment;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		logger.info("retriveExchangeValue clled with from {} to {}", from, to);
		CurrencyExchange currencyExchange = null;
		//currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(85.6));
		currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data for "+from+" and "+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
