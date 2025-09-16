package com.darp.designpatterns.domain.patterns.observer;

public interface Subject<T> {
  void addObserver(Observer<T> observer);

  void removeObserver(Observer<T> observer);

  void notifyObservers();
}
