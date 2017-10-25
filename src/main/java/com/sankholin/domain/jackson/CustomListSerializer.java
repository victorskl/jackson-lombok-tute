package com.sankholin.domain.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListSerializer extends JsonSerializer<List<J5Item>> {

    @Override
    public void serialize(List<J5Item> items, JsonGenerator generator, SerializerProvider provider)
            throws IOException {

        List<Integer> ids = new ArrayList<>();

        for (J5Item item : items) {
            ids.add(item.id);
        }

        generator.writeObject(ids);
    }
}
