package code.data.enums;

public enum RoomState {
    archived, nonArchived;

    public static RoomState getValue(String value) {
        for (RoomState f : RoomState.values()) {
            if (f.name().compareToIgnoreCase(value) == 0) {
                return f;
            }
        }
        return null;
    }

}