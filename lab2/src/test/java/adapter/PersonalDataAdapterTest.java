package adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class PersonalDataAdapterTest {

    private PersonalDataI personalData;
    private PersonalInformation personalInformation;

    @BeforeEach
    public void setUp() {
        personalData = new PersonalData(
            "John Doe",
            LocalDate.of(1990, 5, 15),
            "john.doe@email.com",
            "+1234567890"
        );
        personalInformation = new PersonalDataAdapter(personalData);
    }

    @Test
    public void testPersonalDataCreation() {
        assertEquals("John Doe", personalData.getName());
        assertEquals(LocalDate.of(1990, 5, 15), personalData.getBDay());
        assertEquals("john.doe@email.com", personalData.getEmail());
        assertEquals("+1234567890", personalData.getTelephone());
    }

    @Test
    public void testAdapterReturnsCorrectJSON() {
        String expectedJson = "{\"name\":\"John Doe\",\"yearOfBirth\":1990,\"email\":\"john.doe@email.com\",\"telephone\":\"+1234567890\"}";
        String actualJson = personalInformation.getPersonalInformation();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testAdapterWithDifferentData() {
        PersonalDataI anotherPerson = new PersonalData(
            "Jane Smith",
            LocalDate.of(1985, 12, 25),
            "jane.smith@company.com",
            "+9876543210"
        );
        PersonalInformation anotherAdapter = new PersonalDataAdapter(anotherPerson);

        String expectedJson = "{\"name\":\"Jane Smith\",\"yearOfBirth\":1985,\"email\":\"jane.smith@company.com\",\"telephone\":\"+9876543210\"}";
        String actualJson = anotherAdapter.getPersonalInformation();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testAdapterWithSpecialCharacters() {
        PersonalDataI specialPerson = new PersonalData(
            "José María",
            LocalDate.of(2000, 1, 1),
            "jose.maria@test.org",
            "+34-123-456-789"
        );
        PersonalInformation specialAdapter = new PersonalDataAdapter(specialPerson);

        String expectedJson = "{\"name\":\"José María\",\"yearOfBirth\":2000,\"email\":\"jose.maria@test.org\",\"telephone\":\"+34-123-456-789\"}";
        String actualJson = specialAdapter.getPersonalInformation();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testAdapterWithLeapYear() {
        PersonalDataI leapYearPerson = new PersonalData(
            "Leap Year Baby",
            LocalDate.of(2000, 2, 29),
            "leap@year.com",
            "+0000000000"
        );
        PersonalInformation leapAdapter = new PersonalDataAdapter(leapYearPerson);

        String actualJson = leapAdapter.getPersonalInformation();
        assertTrue(actualJson.contains("\"yearOfBirth\":2000"));
        assertTrue(actualJson.contains("\"name\":\"Leap Year Baby\""));
    }

    @Test
    public void testJSONFormat() {
        String json = personalInformation.getPersonalInformation();

        assertTrue(json.startsWith("{"));
        assertTrue(json.endsWith("}"));
        assertTrue(json.contains("\"name\":"));
        assertTrue(json.contains("\"yearOfBirth\":"));
        assertTrue(json.contains("\"email\":"));
        assertTrue(json.contains("\"telephone\":"));
    }
}