package com.syneation.shortlinks.dto.links;

import jakarta.validation.constraints.NotEmpty;

public class LinksDto {

    @NotEmpty
    private Long id_who_created;

    @NotEmpty
    private String original_link;

    @NotEmpty
    private String new_link;

    public Long getId_who_created() {
        return id_who_created;
    }

    public void setId_who_created(Long id) {
        id_who_created = id;
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
}
