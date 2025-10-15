package adapter;

public class PersonalDataAdapter implements PersonalInformation {
    private PersonalDataI personalData;

    public PersonalDataAdapter(PersonalDataI personalData) {
        this.personalData = personalData;
    }

    @Override
    public String getPersonalInformation() {
        int yearOfBirth = personalData.getBDay().getYear();

        return String.format(
            "{\"name\":\"%s\",\"yearOfBirth\":%d,\"email\":\"%s\",\"telephone\":\"%s\"}",
            personalData.getName(),
            yearOfBirth,
            personalData.getEmail(),
            personalData.getTelephone()
        );
    }
}