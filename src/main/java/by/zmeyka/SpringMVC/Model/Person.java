package by.zmeyka.SpringMVC.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;

  @NonNull
    private String name;
  @NonNull
    private String surname;



}
