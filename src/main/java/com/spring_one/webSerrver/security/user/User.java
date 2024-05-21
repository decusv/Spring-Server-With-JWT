package com.spring_one.demo.security.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// @Data generates toString, getters and setters for all fields defined in the class.
@Data
// Generates a builder pattern for this class.
@Builder
// Generates a constructor with no arguments. Needed with Hibernate in order to instantiate objects.
@NoArgsConstructor
// Generates a constructor with all arguments i.e., all defined fields.
@AllArgsConstructor
@Entity
@Table
public class User implements UserDetails {

   @jakarta.persistence.Id
   @SequenceGenerator(
           // Defines the name of this custom sequence generator. This attribute is used to uniquely identify the sequence generator inside the @GeneratedValue annotation.
           name = "user_sequence",
           // Specifies the name of the database sequence that the generator is associated with. This is defined inside the DBMS itself i.e., PostgreSQL
           sequenceName = "user_sequence",
           // Specifies the allocation size for fetching values from a sequence.
           allocationSize = 1
   )

   // This annotation is used to specify the strategy used to generate the primary key for entities inside the persistent database.
   @GeneratedValue(
           //
           strategy = GenerationType.SEQUENCE,
           generator = "user_sequence"
   )
   private Long Id;
   private String firstname;
   private String lastname;
   private String email;
   private String password;

   @Enumerated(EnumType.STRING)
   private Role role;


   // SimpleGrantedAuthority is a wrapper around roles or permissions that would be defined and assign to users.
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(role.name()));
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
