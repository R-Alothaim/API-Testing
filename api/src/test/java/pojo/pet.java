package pojo;

import java.util.List;

public class pet {
  private int id;
  private category category;
  private String name;
  private String[] photoUrls;
  private List<tags> tags;
  private String status;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public category getCategory() {
    return this.category;
  }

  public void setCategory(category category) {
    this.category = category;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getPhotoUrls() {
    return this.photoUrls;
  }

  public void setPhotoUrls(String[] photoUrls) {
    this.photoUrls = photoUrls;
  }

  public List<tags> getTags() {
    return this.tags;
  }

  public void setTags(List<tags> tags) {
    this.tags = tags;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
