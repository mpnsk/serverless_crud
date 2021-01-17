package com.github.mpnsk.serverless_crud;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.FieldNameConstants;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
@FieldNameConstants(asEnum = true, level = AccessLevel.PUBLIC)
public class Game {
    public static String tableName = "meine-spiele";

    public String id;
    public String title;
    public String description;
    public int year;
}
