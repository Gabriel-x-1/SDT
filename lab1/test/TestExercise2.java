public class TestExercise2 {
    public static void main(String[] args) {
        System.out.println("=== Testing Exercise 2 - Builder Pattern ===");

        Car fordTrend = new Car.Builder("Ford", 2009, 87, "diesel", "XYZ").build();
        System.out.println("Ford Trend: " + fordTrend);

        Car fordTitanium = new Car.Builder("Ford", 2018, 125, "diesel", "WWW")
                .sound(Sound.MP3)
                .navigation(Navigation.SMALL)
                .build();
        System.out.println("Ford Titanium: " + fordTitanium);

        Car fordEco = new Car.Builder("Ford", 2019, 100, "gas", "YHD")
                .air(Air.AUTO)
                .build();
        System.out.println("Ford Eco: " + fordEco);

        System.out.println("\n=== Testing default values ===");
        System.out.println("Ford Trend defaults - Sound: " + fordTrend.getSoundSystem() +
                           ", Navigation: " + fordTrend.getNavigation() +
                           ", Air: " + fordTrend.getAirConditioning());

        System.out.println("\n=== Testing all optional parameters ===");
        Car fullyLoaded = new Car.Builder("BMW", 2023, 300, "electric", "ABC123")
                .sound(Sound.Premium)
                .navigation(Navigation.GPS)
                .air(Air.CLIMATE)
                .build();
        System.out.println("Fully loaded BMW: " + fullyLoaded);

        System.out.println("Exercise 2 tests completed successfully!");
    }
}