public enum NodeStateEnum {
    INVALID("NO SUCH TRACE");

    private final String message;

    NodeStateEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
