package com.syneation.shortlinks.controllers.account.links;

import com.syneation.shortlinks.Repository.LinksRepository;
import com.syneation.shortlinks.Repository.UserRepository;
import com.syneation.shortlinks.Security.UserPrincipal;
import com.syneation.shortlinks.dto.links.LinksDto;
import com.syneation.shortlinks.entity.Links;
import com.syneation.shortlinks.entity.Users;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/links/new")
    public String createLinkPage(
            Model model,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        if (userPrincipal != null) {
            LinksDto linksDto = new LinksDto();

            model.addAttribute("name", userPrincipal.getUsername());
            model.addAttribute("linksDto", linksDto);
            model.addAttribute("currentId", userPrincipal.getUsers().getId());

            return "account/links/new";
        }

        return "redirect:/login";
    }

    @PostMapping("/profile/links/new")
    public String createLink(
            Model model,
            @Valid @ModelAttribute LinksDto linksDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {

        try {
            Links newLinks = new Links();
            newLinks.setOriginal_link(linksDto.getOriginal_link());
            newLinks.setNew_link("https://sl/gAfcxzdgsa");
            newLinks.setCreated_at(new Date());
            newLinks.setUpdated_at(new Date());

            Users user = userRepository.findById(linksDto.getCreator())
                    .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
            newLinks.setCreator(user);

            linksRepo.save(newLinks);

        } catch (Exception e) {
            if (userPrincipal != null &&
                    ("programmer".equals(userPrincipal.getRole())
                            || "admin".equals(userPrincipal.getRole()))) {
                model.addAttribute("error", e.getMessage());
            } else {
                model.addAttribute("error", "ERROR: произошла ошибка создания ссылки");
            }
            return "account/links/new";
        }

        model.addAttribute("linksDto", new LinksDto());
        model.addAttribute("new_link", "https://sl/gAfcxzdgsa");
        model.addAttribute("success", "Ссылка успешно создана!");

        return "/account/links/new";
    }

}
