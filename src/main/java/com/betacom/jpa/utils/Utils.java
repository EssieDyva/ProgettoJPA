package com.betacom.jpa.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.betacom.jpa.exceptions.AcademyException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static LocalDate stringToDate(String myDate) throws AcademyException {
		LocalDate r = null;
		try {
			log.debug("Date: " + myDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALIAN);
			r = LocalDate.parse(myDate, formatter);
			
		} catch (DateTimeParseException e) {
			throw new AcademyException("Formato della data invalido " + myDate + " " + e.getMessage());
		}
		return r;
	}
}
