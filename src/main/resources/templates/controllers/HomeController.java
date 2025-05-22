package templates.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.services.GanacheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final GanacheService ganacheService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Halo dari Spring Boot!");
        return "index"; // akan render index.html dari folder templates
    }

    @PostMapping("/submitName")
    public String submitName(@ModelAttribute SmartContractRequest request, Model model) {

        String greeting = "Halo, " + request.getNama() + "! Selamat datang.";

        log.info(greeting);
        log.info("Request : ", request);

        ganacheService.crud(request);

        // Masukkan hasil ke model agar bisa ditampilkan di halaman
        model.addAttribute("greeting", greeting);

        // Tampilkan kembali halaman index dengan greeting
        return "index";
    }
}
