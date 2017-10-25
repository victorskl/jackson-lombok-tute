package com.sankholin.domain.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "id", "name" })
public class J5User {
    public int id;
    public String name;

    @JsonSerialize(using = CustomListSerializer.class)
    @JsonDeserialize(using = CustomListDeserializer.class)
    public List<J5Item> userItems;
}
