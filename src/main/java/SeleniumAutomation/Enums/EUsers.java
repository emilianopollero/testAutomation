package SeleniumAutomation.Enums;

public enum EUsers {
    ADMIN("admin"),
    DEV("dev"),
    TESTER("tester");

    private String displayName;

    EUsers(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return displayName;
    }
}
