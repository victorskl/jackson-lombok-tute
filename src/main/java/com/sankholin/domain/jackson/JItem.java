package com.sankholin.domain.jackson;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JItem {
    public int id;
    public String itemName;

    @JsonManagedReference
    public JUser owner;
}
