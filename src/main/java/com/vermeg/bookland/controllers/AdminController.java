package com.vermeg.bookland.controllers;

import com.vermeg.bookland.dtos.BookVenteStatDto;
import com.vermeg.bookland.dtos.ChiffreAffaireBookDto;
import com.vermeg.bookland.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CommandeRepository commandeRepository;

    @Autowired
    public AdminController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/home")
    public String home(Model model){
        return "redirect:/stat/stat";
    }
}
