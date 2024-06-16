package com.movie.movie.Model.Request;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequest  implements Serializable{
    @NotEmpty
    @Size(min = 2, message = "movieId can not be less than 2  ")
    long movieid;

    @NotEmpty
    @Size(min = 3,message = "Director's name can not be lessed than 3 haha")
    String directorname;
}
