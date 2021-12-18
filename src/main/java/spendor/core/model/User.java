package spendor.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String username;
  private String fullname;
  private String email;
  private String password;
  private String phone;
  private boolean active;
  // TODO: adding role
  // TODO: adding salt password

  public User() {

  }

  public User( String username, String fullname, String email, String password, String phone, boolean active ) {
      this.username = username;
      this.fullname = fullname;
      this.email = email;
      this.password = password;
      this.phone = phone;
      this.active = active;
  }

  public String getId() {
    return id;
  }
  public String getUsername() {
    return username;
  }
  public String getFullname() {
    return fullname;
  }
  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }
  public String getPhone() {
    return phone;
  }
  public boolean getActive() {
    return active;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", email=" + email  + ", active=" + active + "]"; 
  }
}
