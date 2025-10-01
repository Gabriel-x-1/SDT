public class Car {
    private final String brand;
    private final int productionYear;
    private final int enginePower;
    private final String fuelType;
    private final String chassisNumber;
    private final Sound soundSystem;
    private final Navigation navigation;
    private final Air airConditioning;

    private Car(Builder builder) {
        this.brand = builder.brand;
        this.productionYear = builder.productionYear;
        this.enginePower = builder.enginePower;
        this.fuelType = builder.fuelType;
        this.chassisNumber = builder.chassisNumber;
        this.soundSystem = builder.soundSystem;
        this.navigation = builder.navigation;
        this.airConditioning = builder.airConditioning;
    }

    public String getBrand() {
        return brand;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public Sound getSoundSystem() {
        return soundSystem;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Air getAirConditioning() {
        return airConditioning;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", productionYear=" + productionYear +
                ", enginePower=" + enginePower +
                ", fuelType='" + fuelType + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", soundSystem=" + soundSystem +
                ", navigation=" + navigation +
                ", airConditioning=" + airConditioning +
                '}';
    }

    public static class Builder {
        private final String brand;
        private final int productionYear;
        private final int enginePower;
        private final String fuelType;
        private final String chassisNumber;
        private Sound soundSystem = Sound.RadioCD;
        private Navigation navigation = Navigation.None;
        private Air airConditioning = Air.MANUAL;

        public Builder(String brand, int productionYear, int enginePower, String fuelType, String chassisNumber) {
            this.brand = brand;
            this.productionYear = productionYear;
            this.enginePower = enginePower;
            this.fuelType = fuelType;
            this.chassisNumber = chassisNumber;
        }

        public Builder sound(Sound sound) {
            this.soundSystem = sound;
            return this;
        }

        public Builder navigation(Navigation navigation) {
            this.navigation = navigation;
            return this;
        }

        public Builder air(Air air) {
            this.airConditioning = air;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}