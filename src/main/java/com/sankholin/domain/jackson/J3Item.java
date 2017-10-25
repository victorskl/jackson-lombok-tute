package com.sankholin.domain.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class J3Item {
    public int id;
    public String itemName;
    public J3User owner;
}
