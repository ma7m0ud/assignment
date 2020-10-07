package com.nagarro.test.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate entityDate) {
    	 return  entityDate.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String databaseDate) {
    		return LocalDate.parse(databaseDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       
    }
}


