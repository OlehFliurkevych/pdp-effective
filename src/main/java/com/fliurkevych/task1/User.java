package com.fliurkevych.task1;

public class User {

  private String name;

  User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "User{" +
      "name='" + name + '\'' +
      '}';
  }
}
