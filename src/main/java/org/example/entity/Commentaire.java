package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Commentaire {
    private int id;
    private String description;
}
