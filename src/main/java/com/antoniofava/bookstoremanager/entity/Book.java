package com.antoniofava.bookstoremanager.entity;

import javax.persistence.*;

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer chapters;

    @Column(nullable = false)
    private String isbn;

    @Column(name = "publisher_name", nullable = false, unique = true)
    private String publisherName;

    @ManyToOne(fetch = FetchType.LAZY cascade = {CascadeType.PERSIST, CascadeType.Merge, CascadeType.REMOVE}
    @JoinColumn(name = "author_id")
    private Author author;


}
