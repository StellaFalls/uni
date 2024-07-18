package code.data.enums;

public enum RoomType {
    solo, duos;

    public static RoomType getValue(String value) {
        for (RoomType f : RoomType.values()) {
            if (f.name().compareToIgnoreCase(value) == 0) {
                return f;
            }
        }
        return null;
    }

}