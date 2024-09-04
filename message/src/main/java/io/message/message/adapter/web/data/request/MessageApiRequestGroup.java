package io.message.message.adapter.web.data.request;

public class MessageApiRequestGroup {

    public record CreateApiRequest(String content) {}

    public record SearchApiRequest(String content) {}
}
