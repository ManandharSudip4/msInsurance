package com.mstech.msinsurancebackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "cat_name")
  private String catName;

  @Column(name = "cat_description")
  private String catDescription;

  @Column(name = "cat_image")
  private String catImage;

  private boolean availability;

  private boolean isFeatured;

  private Date createdDate;

  private Date updatedDate;
}
