package br.edu.ifpb.graphql.lab01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int     id;
    private String  name;
    private int     pageCount;
    private Author  author;
    
}
