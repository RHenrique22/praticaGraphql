package br.edu.ifpb.graphql.lab01.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private int     id;
    private String  firstName;
    private String  lastName;

}
