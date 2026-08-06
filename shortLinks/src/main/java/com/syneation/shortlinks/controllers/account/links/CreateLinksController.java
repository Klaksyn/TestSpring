package com.syneation.shortlinks.controllers.account.links;

import com.syneation.shortlinks.Repository.LinksRepository;
import com.syneation.shortlinks.Security.UserPrincipal;
import com.syneation.shortlinks.dto.links.LinksDto;
import com.syneation.shortlinks.entity.Links;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class CreateLinksController {

    @Autowired
    private LinksRepository linksRepo;

    @GetMapping("/profile/links/new")
    public String createLinkPage(
            Model model,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        if (userPrincipal != null) {
            LinksDto linksDto = new LinksDto();

            model.addAttribute("name", userPrincipal.getUsername());
            model.addAttribute("linksDto", linksDto);

            if (linksDto.getNew_link() != null) {
                model.addAttribute("newLink", linksDto.getNew_link());
            }

            return "account/links/new";
        }

        return "redirect:/login";
    }

    @PostMapping("/profile/links/new")
    public String createLink(
            Model model,
            @Valid @ModelAttribute LinksDto linksDto,
            BindingResult bindingResult
    ) {

        try {
            Links newLinks = new Links();
            newLinks.setOriginal_link(linksDto.getOriginal_link());
            newLinks.setNew_link(linksDto.getNew_link());
            //newLinks.setId_who_created();
            newLinks.setCreated_at(new Date());
            newLinks.setUpdated_at(new Date());

            linksRepo.save(newLinks);
        } catch (Exception e) {

        }

        // TODO
        return "/";
    }

}
