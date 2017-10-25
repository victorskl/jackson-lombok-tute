package com.sankholin.domain.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "id", "itemName", "owner" })
public class J5Item {
    public int id;
    public String itemName;
    public J5User owner;
}
