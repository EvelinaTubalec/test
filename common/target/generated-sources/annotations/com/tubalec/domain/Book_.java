package com.tubalec.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, Integer> numberOfPages;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, Float> price;
	public static volatile SingularAttribute<Book, String> genre;
	public static volatile SingularAttribute<Book, String> publishingHouse;
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> title;

	public static final String NUMBER_OF_PAGES = "numberOfPages";
	public static final String AUTHOR = "author";
	public static final String PRICE = "price";
	public static final String GENRE = "genre";
	public static final String PUBLISHING_HOUSE = "publishingHouse";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

