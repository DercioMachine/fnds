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

    @Column(name = "tipogbv", nullable = true,length=120)
    private String tipogbv="Não";  // Sim e Nao

    @Column(name = "tipourgente", nullable = true,length=120)
    private String tipourgente="Não";  // Sim e Nao

    @ManyToOne
    @JoinColumn(name="provincia_id")
    private Provincia provincia;



    @Column(name = "active", nullable = true)
    private boolean active=true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private List<Role> roles;

    public String getTipogbv() {
        return tipogbv;
    }

    public void setTipogbv(String tipogbv) {
        this.tipogbv = tipogbv;
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

    public String getTipourgente() {
        return tipourgente;
    }

    public void setTipourgente(String tipourgente) {
        this.tipourgente = tipourgente;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
