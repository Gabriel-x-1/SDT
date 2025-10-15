import adapter.*;
import java.time.LocalDate;

public class TestAdapter {
    public static void main(String[] args) {
        System.out.println("=== Exercise 2: Adapter Pattern Demo ===");

        // Create PersonalData instance
        PersonalDataI personalData = new PersonalData(
            "John Doe",
            LocalDate.of(1990, 5, 15),
            "john.doe@email.com",
            "+1234567890"
        );

        System.out.println("Original PersonalData methods:");
        System.out.println("Name: " + personalData.getName());
        System.out.println("Birth Date: " + personalData.getBDay());
        System.out.println("Email: " + personalData.getEmail());
        System.out.println("Telephone: " + personalData.getTelephone());

        // Use adapter to convert to PersonalInformation interface
        PersonalInformation personalInfo = new PersonalDataAdapter(personalData);

        System.out.println("\nAdapted to PersonalInformation JSON format:");
        System.out.println(personalInfo.getPersonalInformation());

        // Test with different person
        PersonalDataI anotherPerson = new PersonalData(
            "Jane Smith",
            LocalDate.of(1985, 12, 25),
            "jane.smith@company.com",
            "+9876543210"
        );

        PersonalInformation anotherInfo = new PersonalDataAdapter(anotherPerson);
        System.out.println("\nAnother person in JSON format:");
        System.out.println(anotherInfo.getPersonalInformation());

        System.out.println("\nAdapter Pattern test completed successfully!");
    }
}