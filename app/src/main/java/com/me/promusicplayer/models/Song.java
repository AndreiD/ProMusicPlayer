package com.me.promusicplayer.models;

public class Song {

  private String url;
  private String name;
  private String artist;

  public Song(String url, String name, String artist) {
    this.url = url;
    this.name = name;
    this.artist = artist;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  @Override public String toString() {
    return "Song{" +
        "url='" + url + '\'' +
        ", name='" + name + '\'' +
        ", artist='" + artist + '\'' +
        '}';
  }
}
