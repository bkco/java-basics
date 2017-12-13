package co.bk.javabasics.main.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Value;


import java.io.Serializable;

/**
 * Model class to hold ContactDetails Information.
 */
@Builder(toBuilder = true)
@Value
@JsonDeserialize(builder = Translation.TranslationBuilder.class)
@JsonSerialize(using = TranslationSerializer.class)
public class Translation implements Serializable {
    private static final long serialVersionUID = 2716673948165345545L;

    private final String language; //en,de,fr
    private final String text;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class TranslationBuilder {
    }
}
