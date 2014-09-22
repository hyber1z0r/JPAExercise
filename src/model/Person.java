package model;

/**
 * @author Lars
 */
public class Person {
  String fName;
  String lName;
  String phone;
  int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Person(String fName, String lName, String phone) {
    this.fName = fName;
    this.lName = lName;
    this.phone = phone;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  
}
