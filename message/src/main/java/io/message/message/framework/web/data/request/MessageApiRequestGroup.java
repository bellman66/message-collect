package io.message.message.framework.web.data.request;

public class MessageApiRequestGroup {

    public record CreateApiRequest(String content) {
    }

    public record SearchApiRequest(String content) {
    }

}
