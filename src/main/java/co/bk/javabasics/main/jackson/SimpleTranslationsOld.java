package co.bk.javabasics.main.jackson;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://stackoverflow.com/questions/14515994/convert-json-string-to-pretty-print-json-output-using-jackson
 *
 * Desired JSON format:
 *   		const TRANSLATIONS = {
                 en_US: {
                 title: 'Multilingual React Application',
                 table_caption: 'Translation Example',
                 },
                 fr_BE: {
                 title: 'Application Multilingue React',
                 table_caption: 'Exemple de traduction',
                 },
                 nl_BE: {
                 title: 'Meertalige React Applicatie',
                 table_caption: 'Vertaling Voorbeeld',
                 },
                 zh_CN: {
                 title: '多语言 React 申请',
                 table_caption: '翻译实例',
                 }
                 };
 */
public class SimpleTranslationsOld {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        //For testing
        User user = createDummyUser();

        try {
            //Convert object to JSON string and save into file directly
            mapper.writeValue(new File("/tmp/user.json"), user);

            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(user);
            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            //jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            jsonInString = mapper.defaultPrettyPrintingWriter().writeValueAsString(user);

            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static User createDummyUser(){

        SimpleTranslationsOld stj = new SimpleTranslationsOld();
        User user = stj.new User();

        user.setName("mkyong");
        user.setAge(33);

        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");

        user.setMessages(msg);

        Map<String, String> translations = new HashMap<>();
        translations.put("en", "bye!");
        translations.put("de", "ciao!");
        user.setTranslations(translations);

        return user;

    }

    public class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }

        private String name;

        public Map<String, String> getTranslations() {
            return translations;
        }

        public void setTranslations(Map<String, String> translations) {
            this.translations = translations;
        }

        private int age;
        private List<String> messages;

        private Map<String, String> translations;

        //getters and setters
    }
}