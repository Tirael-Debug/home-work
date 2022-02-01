package com.sbrf.reboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 1030177482775116474L;

    @Getter
    private String atmNumber;

}
