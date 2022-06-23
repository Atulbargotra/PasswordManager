package org.gradientworks.passwordmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "authorities")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Authority> authorities = new ArrayList<>();

    public User(String name, String username, String password, Set<Authority> authorities) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities.addAll(authorities);
    }

    public User(String name, String username, String password, Authority... authorities) {
        this(name, username, password, new HashSet<>(Arrays.asList(authorities)));
    }

    public User(String name, String username, String password) {
        this(name, username, password, new HashSet<>());
    }
}
