package com.future.observermonitorpublic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicStatisDTO {

    private Integer userId;

    private Date date;
}
