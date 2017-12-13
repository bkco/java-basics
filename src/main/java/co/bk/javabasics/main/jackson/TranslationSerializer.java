package co.bk.javabasics.main.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;


class TranslationSerializer extends StdSerializer<Translation> {

    public TranslationSerializer() {
        this(null);
    }

    public TranslationSerializer(Class<Translation> t) {
        super(t);
    }

    @Override
    public void serialize(
            Translation value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        /*
          "translations" : [
              {
                "en" : {"text":english translation}
              }, {
                "de" : {"text":deutsch Ubersetzung}
              }
           ]
         */
        String profileString = "{\"text\":%s}";
        jgen.writeStartObject();
        jgen.writeFieldName(value.getLanguage());
        jgen.writeRawValue(String.format(profileString,value.getText()));
        jgen.writeEndObject();
    }

//    /**
//     *   "translations" : [ {
//     "languageYo" : "en",
//     "textYo" : "english translation"
//     }, {
//     "languageYo" : "de",
//     "textYo" : "deutsch Ubersetzung"
//     } ]
//     * @param value
//     * @param jgen
//     * @param provider
//     * @throws IOException
//     * @throws JsonProcessingException
//     */
//    @Override
//    public void serialize(
//            Translation value, JsonGenerator jgen, SerializerProvider provider)
//            throws IOException, JsonProcessingException {
//
//        jgen.writeStartObject();
//        //jgen.writeNumberField(, value.getLanguage());
//        jgen.writeStringField("languageYo", value.getLanguage());
//        jgen.writeStringField("textYo", value.getText());
//        jgen.writeEndObject();
//    }


//    @Override
//    public void serialize(Translation swe,
//            JsonGenerator jgen,
//            SerializerProvider sp) throws IOException, JsonGenerationException {
//
//        StringBuilder lang = new StringBuilder();
//        jgen.writeStartObject();
//        jgen.writeNumberField("id", swe.getId());
//        jgen.writeStringField("name", swe.getName());
//
//        for (String s: swe.getLanguages()) {
//            lang.append(s).append(";");
//        }
//        jgen.writeStringField("languages", lang.toString());
//
//        jgen.writeEndObject();
//    }
}
