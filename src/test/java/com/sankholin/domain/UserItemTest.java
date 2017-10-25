package com.sankholin.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankholin.domain.jackson.*;
import com.sankholin.domain.simple.Item;
import com.sankholin.domain.simple.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class UserItemTest {

    // http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    // https://github.com/eugenp/tutorials/tree/master/jackson

    @Test(expected = JsonMappingException.class)
    //@Test  // switch this to see the JsonMappingException: Infinite recursion (StackOverflowError)
    public void
    givenBidirectionRelation_whenSerializing_thenException()
            throws JsonProcessingException {

        User user = new User(1, "John", new ArrayList<>());
        Item item = new Item(2, "book", user);
        user.getUserItems().add(item);

        new ObjectMapper().writeValueAsString(item);
    }

    @Test
    public void
    givenBidirectionRelation_whenUsingJacksonReferenceAnnotation_thenCorrect()
            throws JsonProcessingException {

        JUser user = new JUser(1, "John", new ArrayList<>());
        JItem item = new JItem(2, "book", user);
        user.getUserItems().add(item);

        String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);

        System.out.println("JUser : givenBidirectionRelation_whenUsingJacksonReferenceAnnotation_thenCorrect");
        System.out.println(result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));
    }

    @Test
    public void
    givenBidirectionRelation_whenUsingJsonIdentityInfo_thenCorrect()
            throws JsonProcessingException {

        J2User user = new J2User(1, "John", new ArrayList<>());
        J2Item item = new J2Item(2, "book", user);
        user.getUserItems().add(item);

        String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);

        System.out.println("J2User : givenBidirectionRelation_whenUsingJsonIdentityInfo_thenCorrect");
        System.out.println(result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, containsString("userItems"));
    }

    @Test
    public void
    givenBidirectionRelation_whenUsingJsonIgnore_thenCorrect()
            throws JsonProcessingException {

        J3User user = new J3User(1, "John", new ArrayList<>());
        J3Item item = new J3Item(2, "book", user);
        user.getUserItems().add(item);

        String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);

        System.out.println("J3User : givenBidirectionRelation_whenUsingJsonIgnore_thenCorrect");
        System.out.println(result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));
    }

    @Test
    public void givenBidirectionRelation_whenUsingPublicJsonView_thenCorrect()
            throws JsonProcessingException {

        J4User user = new J4User(1, "John", new ArrayList<>());
        J4Item item = new J4Item(2, "book", user);
        user.getUserItems().add(item);

        String result = new ObjectMapper().writerWithView(Views.Public.class) // Note: with Public View
                .withDefaultPrettyPrinter().writeValueAsString(item);

        System.out.println("J4User : givenBidirectionRelation_whenUsingPublicJsonView_thenCorrect");
        System.out.println(result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));
    }

    @Test(expected = JsonMappingException.class)
    public void
    givenBidirectionRelation_whenUsingInternalJsonView_thenException()
            throws JsonProcessingException {

        J4User user = new J4User(1, "John", new ArrayList<>());
        J4Item item = new J4Item(2, "book", user);
        user.getUserItems().add(item);

        new ObjectMapper()
                .writerWithView(Views.Internal.class) // Note: with Internal View
                .writeValueAsString(item);
    }

    @Test
    public void
    givenBidirectionRelation_whenUsingCustomSerializer_thenCorrect()
            throws JsonProcessingException {

        J5User user = new J5User(1, "John", new ArrayList<>());
        J5Item item = new J5Item(2, "book", user);
        user.getUserItems().add(item);

        String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);

        System.out.println("J5User : givenBidirectionRelation_whenUsingCustomSerializer_thenCorrect");
        System.out.println(result);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, containsString("userItems"));
    }

    @Test
    public void
    givenBidirectionRelation_whenDeserializingWithIdentity_thenCorrect()
            throws JsonProcessingException, IOException {

        String json =
                "{\"id\":2,\"itemName\":\"book\",\"owner\":{\"id\":1,\"name\":\"John\",\"userItems\":[2]}}";

        J2Item item = new ObjectMapper().readerFor(J2Item.class).readValue(json);

        assertEquals(2, item.id);
        assertEquals("book", item.itemName);
        assertEquals("John", item.owner.name);

        System.out.println("J2User : givenBidirectionRelation_whenDeserializingWithIdentity_thenCorrect");
        System.out.println(item.toString());
    }

    @Test
    public void
    givenBidirectionRelation_whenUsingCustomDeserializer_thenCorrect()
            throws JsonProcessingException, IOException {

        String json =
                "{\"id\":2,\"itemName\":\"book\",\"owner\":{\"id\":1,\"name\":\"John\",\"userItems\":[2]}}";

        J5Item item = new ObjectMapper().readerFor(J5Item.class).readValue(json);

        assertEquals(2, item.id);
        assertEquals("book", item.itemName);
        assertEquals("John", item.owner.name);

        System.out.println("J5User : givenBidirectionRelation_whenUsingCustomDeserializer_thenCorrect");
        System.out.println(item.toString());
    }
}
