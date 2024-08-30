package com.movie.movie.Model.Request;

import java.io.Serializable;

import com.movie.movie.Validators.ValidateInputs;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@ValidateInputs(movieid = "movieid",directorname = "directorname")
public class MovieRequest  implements Serializable{
  
    
    @Size(min = 2, message = "movieId can not be less than 2  ")
    String movieid;

    @NotEmpty
    @Size(min = 3,message = "Director's name can not be lessed than 3 haha")
    String directorname;
}
