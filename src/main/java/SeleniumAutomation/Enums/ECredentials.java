package SeleniumAutomation.Enums;

public enum ECredentials {
    ADMIN_USER("admin"),
    ADMIN_USER_PASS("hero"),
    DEV_USER("dev"),
    DEV_USER_PASS("wizard"),
    TESTER_USER("tester"),
    TESTER_USER_PASS("maniac");
    private String displayName;

    ECredentials(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return displayName;
    }
}
