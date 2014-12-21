package ru.mephi.hytech.clustering.util;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException,
			JsonProcessingException {
		JsonNode node = arg0.getCodec().readTree(arg0);
		String date = node.asText();
		return LocalDate.parse(date);
	}

}
