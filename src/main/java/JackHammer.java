import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackHammer {
    public static void main(String args[]){
        Object studentArray = null;
        if (args.length > 1) {
            System.out.println("Creating a Guava Student array.");
            GuavaArray gArray = new GuavaArray(10);
            gArray.set(0, 2.0);
            studentArray = gArray;
        }
        do {
            JackHammer tester = new JackHammer();
            try {
                ObjectMapper mapper = new ObjectMapper();

                Map<String, Object> studentDataMap = new HashMap<String, Object>();
                int[] marks = {1, 2, 3};

                Student student = new Student();
                student.setAge(10);
                student.setName("Mahesh");
                // JAVA Object
                studentDataMap.put("student", student);
                // JAVA String
                studentDataMap.put("name", "Mahesh Kumar");
                // JAVA Boolean
                studentDataMap.put("verified", Boolean.FALSE);
                // Array
                studentDataMap.put("marks", marks);

                mapper.writeValue(new File("student.json"), studentDataMap);
                //result student.json
                //{
                //   "student":{"name":"Mahesh","age":10},
                //   "marks":[1,2,3],
                //   "verified":false,
                //   "name":"Mahesh Kumar"
                //}
                studentDataMap = mapper.readValue(new File("student.json"), Map.class);

                System.out.println(studentDataMap.get("student"));
                System.out.println(studentDataMap.get("name"));
                System.out.println(studentDataMap.get("verified"));
                System.out.println(studentDataMap.get("marks"));
                if (studentArray != null) {
                    System.out.println("we have a (guava) student array");
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        } while (true);
    }
}
