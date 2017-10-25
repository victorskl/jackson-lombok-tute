package com.sankholin.domain.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class J3User {
    public int id;
    public String name;

    @JsonIgnore
    public List<J3Item> userItems;
}
