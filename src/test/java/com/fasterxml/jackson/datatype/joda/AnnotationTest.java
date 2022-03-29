package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class AnnotationTest extends DateTimeTest
{
    static class AClass{
        @JsonSerialize(using = DateTimeSerializer.class)
        @JsonDeserialize(using = DateTimeDeserializer.class)
        private DateTime createdOn = DateTime.now(DateTimeZone.UTC);

        public DateTime getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(DateTime createdOn) {
            this.createdOn = createdOn;
        }
    }

    public void testDateTimeViaAnnotation() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AClass initialObject = new AClass();
        String serializedObject = objectMapper.writeValueAsString(initialObject);
        AClass deserializedObject = objectMapper.readValue(serializedObject, AClass.class);
        assertEquals(deserializedObject.getCreatedOn(), initialObject.getCreatedOn());
    }

}
