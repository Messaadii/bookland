package com.vermeg.bookland.controllers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vermeg.bookland.dtos.BookVenteStatDto;
import com.vermeg.bookland.dtos.ChiffreAffaireBookDto;
import com.vermeg.bookland.repositories.CommandeRepository;


@Controller
@RequestMapping("/stat/")
public class StatController {
    private final CommandeRepository commandeRepository;

    @Autowired
    public StatController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/stat")
    public String barGraph(Model model) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<BookVenteStatDto> list = this.commandeRepository.findTotalVenteBybook();
        Map<String, Integer> mapchif = new LinkedHashMap<>();
        List<ChiffreAffaireBookDto> listchif = this.commandeRepository.findChiffreAffaireYear();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getTitle(), list.get(i).gettotalVenteBook());
        }
        for (int i = 0; i < listchif.size(); i++) {
            mapchif.put(listchif.get(i).getDate(), listchif.get(i).getTotal());
        }

        System.out.println(mapchif);
        model.addAttribute("map", map);
        model.addAttribute("mapchif", mapchif);
        return "stat/statistic";

    }
}
