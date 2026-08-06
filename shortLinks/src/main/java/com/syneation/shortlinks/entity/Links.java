package com.syneation.shortlinks.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "links")
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_who_created", nullable = false)
    private Users id_who_created;

    private String original_link;
    private String new_link;

    private Date created_at;
    private Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getId_who_created() {
        return id_who_created;
    }

    public void setId_who_created(Users id_who_created) {
        this.id_who_created = id_who_created;
    }

    public String getOriginal_link() {
        return original_link;
    }

    public void setOriginal_link(String original_link) {
        this.original_link = original_link;
    }

    public String getNew_link() {
        return new_link;
    }

    public void setNew_link(String new_link) {
        this.new_link = new_link;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
