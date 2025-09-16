package com.darp.designpatterns.domain.patterns.observer;

public interface Observer<T> {
  void update(T subject);
}
