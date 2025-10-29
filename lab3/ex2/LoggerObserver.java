package ex2;

public class LoggerObserver implements CharacterObserver {
    @Override
    public void onModified(Character character, String message) {
        System.out.println("[LOG] " + message);
    }
}