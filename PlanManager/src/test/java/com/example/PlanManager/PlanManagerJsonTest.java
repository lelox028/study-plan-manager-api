package com.example.PlanManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PlanManagerJsonTest {

    @Autowired
    private JacksonTester<Plan> json;

    @Test
    void planSerializationTest() throws IOException {
        Plan plan = new Plan(99L, "First Plan");
        assertThat(json.write(plan)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(plan)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(plan)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(plan)).hasJsonPathStringValue("@.name");
        assertThat(json.write(plan)).extractingJsonPathStringValue("@.name").isEqualTo("First Plan");
    }

    @Test
    void planDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "name": "First Plan"
                }
                    """;
                assertThat(json.parse(expected)).isEqualTo(new Plan(99L, "First Plan"));
                assertThat(json.parseObject(expected).id()).isEqualTo(99);
                assertThat(json.parseObject(expected).name()).isEqualTo("First Plan");
    }

}
