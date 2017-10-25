package com.sankholin.domain.jackson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JUser {
    public int id;
    public String name;

    @JsonBackReference
    public List<JItem> userItems;
}
