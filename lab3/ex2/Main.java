package ex2;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CharacterRegistry registry = CharacterRegistry.getInstance();
        CharacterPool pool = new CharacterPool(5);
        LoggerObserver logger = new LoggerObserver();

        System.out.println("=== RPG Character Creator ===");

        Character current = null;

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("  1) Create from scratch");
            System.out.println("  2) Use predefined prototype");
            System.out.println("  3) Show prototypes");
            System.out.println("  4) Exit");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();

            if ("1".equals(opt)) {
                // borrow blank from pool
                current = pool.acquire();
                current.addObserver(logger);
                System.out.println("Creating character from scratch. Enter values (press Enter to keep defaults).");

                System.out.print("Name [" + current.getName() + "]: ");
                String name = sc.nextLine().trim();
                if (!name.isEmpty()) current.setName(name);

                System.out.print("Class [" + current.getCharClass() + "]: ");
                String cls = sc.nextLine().trim();
                if (!cls.isEmpty()) current.setCharClass(cls);

                System.out.print("Story (single line): ");
                String story = sc.nextLine();
                if (!story.isEmpty()) current.setStory(story);

                current.setStrength(readStat(sc, "Strength", current.getStrength()));
                current.setConstitution(readStat(sc, "Constitution", current.getConstitution()));
                current.setDexterity(readStat(sc, "Dexterity", current.getDexterity()));
                current.setIntelligence(readStat(sc, "Intelligence", current.getIntelligence()));
                current.setWisdom(readStat(sc, "Wisdom", current.getWisdom()));
                current.setCharisma(readStat(sc, "Charisma", current.getCharisma()));

                System.out.println("\nCharacter created:");
                System.out.println(current);
                // release blank to pool if user wants to discard; we'll keep it live for demonstration
            } else if ("2".equals(opt)) {
                System.out.println("Available prototypes:");
                for (String key : registry.getPrototypes().keySet()) System.out.println("  - " + key);
                System.out.print("Choose prototype name: ");
                String p = sc.nextLine().trim();
                Character clone = registry.getPrototypeClone(p);
                if (clone == null) {
                    System.out.println("Prototype not found.");
                    continue;
                }
                clone.addObserver(logger);
                current = clone;
                System.out.println("Cloned prototype '" + p + "'. You can edit fields now.");

                System.out.print("Name [" + current.getName() + "]: ");
                String name = sc.nextLine().trim();
                if (!name.isEmpty()) current.setName(name);

                System.out.print("Class [" + current.getCharClass() + "]: ");
                String cls = sc.nextLine().trim();
                if (!cls.isEmpty()) current.setCharClass(cls);

                System.out.print("Story (single line) [" + current.getStory() + "]: ");
                String story = sc.nextLine();
                if (!story.isEmpty()) current.setStory(story);

                current.setStrength(readStat(sc, "Strength", current.getStrength()));
                current.setConstitution(readStat(sc, "Constitution", current.getConstitution()));
                current.setDexterity(readStat(sc, "Dexterity", current.getDexterity()));
                current.setIntelligence(readStat(sc, "Intelligence", current.getIntelligence()));
                current.setWisdom(readStat(sc, "Wisdom", current.getWisdom()));
                current.setCharisma(readStat(sc, "Charisma", current.getCharisma()));

                System.out.println("\nFinal character:");
                System.out.println(current);
            } else if ("3".equals(opt)) {
                System.out.println("Prototypes:");
                for (Map.Entry<String, Character> e : registry.getPrototypes().entrySet()) {
                    System.out.println("=== " + e.getKey() + " ===");
                    System.out.println(e.getValue());
                }
            } else if ("4".equals(opt)) {
                System.out.println("Exiting.");
                break;
            } else {
                System.out.println("Unknown option.");
            }
        }

        sc.close();
    }

    private static int readStat(Scanner sc, String label, int current) {
        while (true) {
            System.out.print(label + " [" + current + "] (3-20): ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return current;
            try {
                int v = Integer.parseInt(s);
                if (v < 3 || v > 20) {
                    System.out.println("Value must be between 3 and 20.");
                    continue;
                }
                return v;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a number.");
            }
        }
    }
}