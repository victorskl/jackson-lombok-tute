package com.sankholin.domain.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class J4User {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String name;

    @JsonView(Views.Internal.class)
    public List<J4Item> userItems;
}
