package br.com.xxzidanilloxx.authapi.entity;

import br.com.xxzidanilloxx.authapi.dto.PartnerRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "partner_id")
    private Long id;
    @Column(name = "partner_name")
    private String name;
    @Column(name = "partner_email")
    private String email;
    @Column(name = "partner_website")
    private String website;

    public Partner(String name, String email, String website) {
        this.name = name;
        this.email = email;
        this.website = website;
    }

    public static Partner toEntity(PartnerRequestDTO data){
        return new Partner(
                data.name(),
                data.email(),
                data.website());
    }
}
