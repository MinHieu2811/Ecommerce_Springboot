package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Voucher;
import com.ecommerce.library.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/vouchers")
    public String vouchers(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Voucher> vouchers = voucherService.findAll();
        model.addAttribute("title", "Manage Vouchers");
        model.addAttribute("vouchers", vouchers);
        model.addAttribute("size", vouchers.size());
        return "vouchers";
    }

    @GetMapping("/add-voucher")
    public String addVoucherForm(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("voucher", new Voucher());
        return "add-voucher";
    }

    @PostMapping("/save-voucher")
    public String saveVoucher(@ModelAttribute("voucher") Voucher voucher, RedirectAttributes attributes) {
        try {
            voucherService.addVoucher(voucher); // Updated
            attributes.addFlashAttribute("success", "Voucher added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add voucher!");
        }
        return "redirect:/vouchers";
    }

    @RequestMapping(value = "/delete-voucher/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deleteVoucher(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            voucherService.deleteVoucher(id); // Updated
            attributes.addFlashAttribute("success", "Voucher deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to delete voucher!");
        }
        return "redirect:/vouchers";
    }

}
