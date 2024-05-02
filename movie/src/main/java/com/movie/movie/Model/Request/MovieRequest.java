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
    @Size(min = 3, message = "Supporting role can not be less than 3")
    String supportingRole;

    @NotEmpty
    @Size(min = 3,max = 6,message = "Supporting Id can not be lessed than 3 or greater than 6")
    String supportingId;
}
