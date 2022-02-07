package mz.fipag.grm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "username", nullable = false,length=120,unique=true)
    private String username;

    @Column(name = "password", nullable = true,length=120,unique=true)
    private String password;

    @Column(name = "nome", nullable = true,length=120,unique=true)
    private String nome;

    @Column(name = "telefone", nullable = true,length=120,unique=true)
    private String telefone;

    @Column(name = "email", nullable = true,length=120,unique=true)
    private String email;

    @Column(name = "tipo", nullable = true,length=120)
    private String tipo;  // L-local  R-regional  N-nacional

    @ManyToOne
    @JoinColumn(name="cidade_id")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name="regiao")
    private Regiao regiao;

    @Column(name = "active", nullable = true)
    private boolean active=true;

    @ManyToMany(cascade= CascadeType.MERGE)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private List<Role> roles;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}
