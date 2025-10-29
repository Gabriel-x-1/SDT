package ex2;

import java.util.ArrayList;
import java.util.List;

public class Character implements Cloneable {
    private String name;
    private String charClass;
    private String story;
    private int strength;      // 3..20
    private int constitution;  // 3..20
    private int dexterity;     // 3..20
    private int intelligence;  // 3..20
    private int wisdom;        // 3..20
    private int charisma;      // 3..20

    private final List<CharacterObserver> observers = new ArrayList<>();

    public Character() {
        // defaults
        this.name = "Unnamed";
        this.charClass = "Commoner";
        this.story = "";
        this.strength = this.constitution = this.dexterity = 10;
        this.intelligence = this.wisdom = this.charisma = 10;
    }

    public Character(String name, String charClass, String story,
                     int str, int con, int dex, int intl, int wis, int cha) {
        this.name = name;
        this.charClass = charClass;
        this.story = story;
        this.strength = str;
        this.constitution = con;
        this.dexterity = dex;
        this.intelligence = intl;
        this.wisdom = wis;
        this.charisma = cha;
    }

    public void addObserver(CharacterObserver o) {
        observers.add(o);
    }

    public void removeObserver(CharacterObserver o) {
        observers.remove(o);
    }

    private void notifyObservers(String msg) {
        for (CharacterObserver o : observers) o.onModified(this, msg);
    }

    // setters with validation and notifications
    public void setName(String name) {
        this.name = name;
        notifyObservers("Name set to '" + name + "'");
    }

    public void setCharClass(String cls) {
        this.charClass = cls;
        notifyObservers("Class set to '" + cls + "'");
    }

    public void setStory(String story) {
        this.story = story;
        notifyObservers("Story updated");
    }

    private int clampStat(int v) {
        if (v < 3) return 3;
        if (v > 20) return 20;
        return v;
    }

    public void setStrength(int v) { this.strength = clampStat(v); notifyObservers("Strength = " + this.strength); }
    public void setConstitution(int v) { this.constitution = clampStat(v); notifyObservers("Constitution = " + this.constitution); }
    public void setDexterity(int v) { this.dexterity = clampStat(v); notifyObservers("Dexterity = " + this.dexterity); }
    public void setIntelligence(int v) { this.intelligence = clampStat(v); notifyObservers("Intelligence = " + this.intelligence); }
    public void setWisdom(int v) { this.wisdom = clampStat(v); notifyObservers("Wisdom = " + this.wisdom); }
    public void setCharisma(int v) { this.charisma = clampStat(v); notifyObservers("Charisma = " + this.charisma); }

    // getters
    public String getName() { return name; }
    public String getCharClass() { return charClass; }
    public String getStory() { return story; }
    public int getStrength() { return strength; }
    public int getConstitution() { return constitution; }
    public int getDexterity() { return dexterity; }
    public int getIntelligence() { return intelligence; }
    public int getWisdom() { return wisdom; }
    public int getCharisma() { return charisma; }

    // Prototype pattern: deep-ish clone
    @Override
    public Character clone() {
        return new Character(name, charClass, story,
                strength, constitution, dexterity, intelligence, wisdom, charisma);
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s%nClass: %s%nStory: %s%nStats:%n  STR: %d  CON: %d  DEX: %d%n  INT: %d  WIS: %d  CHA: %d",
                name, charClass, story,
                strength, constitution, dexterity,
                intelligence, wisdom, charisma
        );
    }
}