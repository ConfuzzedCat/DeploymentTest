package dk.lyngby.exception;

public record Message(int status, String message, String timestamp, String appName) {
}
