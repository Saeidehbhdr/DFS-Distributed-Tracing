public enum TraceStateEnum {
    INVALID("NO SUCH TRACE");

    private final String message;

    TraceStateEnum(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
