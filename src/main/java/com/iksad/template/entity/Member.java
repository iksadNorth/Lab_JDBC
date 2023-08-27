package com.iksad.template.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter @NoArgsConstructor
public class Member {
    public static final String TABLE = "member";

    public static final String COL_ID = "id";
    private Long id;

    public static final String COL_NAME = "name";
    private String name;
}
