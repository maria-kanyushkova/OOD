package shape;

public enum Type {
    RECTANGLE("RECTANGLE"),
    TRIANGLE("TRIANGLE"),
    ELLIPSE("ELLIPSE");

    private String text;

    Type(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }

    public static Type fromString(String text) {
        for (Type b : Type.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
