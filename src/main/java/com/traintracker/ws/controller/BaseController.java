package com.traintracker.ws.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.traintracker.ws.exception.DefaultExceptionAttributes;
import com.traintracker.ws.exception.ExceptionAttributes;

public class BaseController {

	/**
	 * The logger for this class hierarchy
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> handleException (
			Exception exception, HttpServletRequest request) {
		logger.error("> handleException");
		logger.error("- Exception: ", exception);
		
		ExceptionAttributes excpAttr = new DefaultExceptionAttributes();
		
		Map<String, Object> responseBody = excpAttr.getExceptionAttributes(
				exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
		logger.error("< handleException");;
		
		return new ResponseEntity<Map<String,Object>> (responseBody,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
}
