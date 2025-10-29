package ex2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton registry of prototype characters (Warrior, Wizard, Rogue...)
 */
public class CharacterRegistry {
    private static CharacterRegistry instance;
    private final Map<String, Character> prototypes = new HashMap<>();

    private CharacterRegistry() {
        loadDefaults();
    }

    public static synchronized CharacterRegistry getInstance() {
        if (instance == null) instance = new CharacterRegistry();
        return instance;
    }

    private void loadDefaults() {
        // create prototypes (no new classes for each character)
        Character warrior = new Character("Conan", "Warrior", "Seasoned fighter",
                18, 16, 12, 8, 10, 9);
        Character wizard = new Character("Merlin", "Wizard", "Keeper of arcane lore",
                8, 10, 12, 18, 14, 11);
        Character rogue = new Character("Lira", "Rogue", "Shadow of the city",
                12, 12, 18, 10, 11, 13);

        prototypes.put("Warrior", warrior);
        prototypes.put("Wizard", wizard);
        prototypes.put("Rogue", rogue);
    }

    public Character getPrototypeClone(String key) {
        Character p = prototypes.get(key);
        if (p == null) return null;
        return p.clone();
    }

    public Map<String, Character> getPrototypes() {
        return Collections.unmodifiableMap(prototypes);
    }

    public void addPrototype(String key, Character prototype) {
        prototypes.put(key, prototype);
    }
}