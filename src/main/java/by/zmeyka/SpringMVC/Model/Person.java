package by.zmeyka.SpringMVC.Model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;

  @NotNull(message="can't be empty")
  @Size(min=2, max=35, message="Name should be shorter.try again.")
    private String name;
    @NotNull(message="can't be empty")
    @Size(min=2, max=35, message="Name should be shorter.try again.")
    private String surname;



}
