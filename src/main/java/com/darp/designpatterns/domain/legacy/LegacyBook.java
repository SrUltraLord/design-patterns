package com.darp.designpatterns.domain.legacy;

public record LegacyBook(
    String code, String name, String author, String type, String format, String status) {}
