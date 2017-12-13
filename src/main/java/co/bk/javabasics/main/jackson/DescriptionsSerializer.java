package co.bk.javabasics.main.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import java.io.IOException;


class DescriptionsSerializer extends StdSerializer<SerializeToJsonUsingDescriptions.Descriptions> {

    /*\
    private String getBeanName(String methodName)
{
    // Assume the method starts with either get or is.
    return Introspector.decapitalize(methodName.substring(methodName.startsWith("is") ? 2 : 3));
}
     */

    public DescriptionsSerializer() {
        this(null);
    }

    public DescriptionsSerializer(Class<SerializeToJsonUsingDescriptions.Descriptions> t) {
        super(t);
    }

//    private String buildJsonTemplate(Class clazz, String methodName, Object objectToInvoke) {
//
//        try {
//            Method method = clazz.getMethod(methodName, null);
//
//            Object oMethodReturned = method.invoke(objectToInvoke, null);
//
//            Class castToType = method.getReturnType();
//
//            Object objectCast = castToType.cast(oMethodReturned);
//
//            //objectCast.get
//
//        } catch (Exception e) {
//        }
//
////        for (Translation translation : value.getUser().getTranslations()) {
////        }
//    }


    // Casting: clazz.cast()...
    // https://stackoverflow.com/questions/8918550/cast-via-reflection-and-use-of-class-cast

    @Override
    public void serialize(
            SerializeToJsonUsingDescriptions.Descriptions value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {


//        int numTranslations = value.getUser().getTranslations().size();
//
//        value.getUser().getTranslations()
        jgen.writeStartObject();

        //StringBuilder jsonString = new StringBuilder("{");
        jgen.writeRawValue("{");
        if (value.getUser() != null && value.getUser().getTranslations() != null) {

            StringBuilder json = new StringBuilder();
            json.append("\"user\":");
            for (Translation translation : value.getUser().getTranslations()) {
                json.append("{\"").append(translation.getLanguage()).append("\": { \"text\":\"").append(translation.getText()).append("\"}");
                jgen.writeRawValue(json.toString());
                jgen.writeRawValue("}");
            }
        }
        jgen.writeRawValue("}");
        jgen.writeEndObject();

        //jsonString.append("}");


        //jgen.writeFieldName("user"); // "en": e.g "user":
        //jgen.writeRawValue(String.format(profileString,value.getUser().getTranslations().get(0).getText()));
        //jgen.writeEndObject();



        // String "%s":"%s"

//        jgen.writeStartObject();
//        jgen.writeFieldName("user"); // "en": e.g "user":
//        jgen.writeRawValue(String.format(profileString,value.getUser().getTranslations().get(0).getText()));
        //jgen.writeEndObject();




        /*
         * Simple test:
         *  {
         *    "user" : {"text":"english translation"}
         *  }
         */
//        String profileString = "{\"text\":\"%s\"}";
//        jgen.writeStartObject();
//        jgen.writeFieldName("user"); // "en": e.g "user":
//        jgen.writeRawValue(String.format(profileString,value.getUser().getTranslations().get(0).getText()));
//        jgen.writeEndObject();


        /*
          "translations" : [
              {
                "en" : {"text":english translation}
              }, {
                "de" : {"text":deutsch Ubersetzung}
              }
           ]
         */
//        String profileString = "{\"text\":%s}";
//        jgen.writeStartObject();
//        jgen.writeFieldName(value.getLanguage());
//        jgen.writeRawValue(String.format(profileString,value.getText()));
//        jgen.writeEndObject();
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
