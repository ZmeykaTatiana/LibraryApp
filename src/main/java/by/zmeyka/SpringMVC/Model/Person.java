package by.zmeyka.SpringMVC.Model;


import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;

  @NotEmpty(message="can't be empty")
  @Size(min=2, max=35, message="Name should be shorter.try again.")
    private String name;
    @NotEmpty(message="can't be empty")
    @Size(min=2, max=35, message="Name should be shorter.try again.")
    private String surname;



}
