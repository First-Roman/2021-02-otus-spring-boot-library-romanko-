package ru.otus.library.controller.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Response {
    OK("{\"result\":\"ok\"}"),
    BAD("{\"result\":\"bad\"}");
    String name;
}
