package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Etape {
    private int id;
    private String description;
}
