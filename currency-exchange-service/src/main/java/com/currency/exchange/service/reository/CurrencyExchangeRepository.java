package com.currency.exchange.service.reository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.exchange.service.controller.dto.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long>{
	CurrencyExchange findByFromAndTo(String from,String to);
}
