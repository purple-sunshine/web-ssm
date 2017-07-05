package com.heitian.ssm.model;

import com.heitian.ssm.arch.base.BaseDomain;
import lombok.Data;

@Data
public class Module extends BaseDomain {
    private String code;
    private String name;
}
