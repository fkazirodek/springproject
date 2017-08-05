package pl.simplebuying.conventer;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConventer implements AttributeConverter<LocalDate, Date>{

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData.toLocalDate();
	}

}
