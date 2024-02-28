package com.data.session;

import com.data.dto.DataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class DataEncoderTemp implements Encoder.Text<DataDto> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(DataDto data) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new EncodeException(data, "Error encoding Data object to JSON", e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
