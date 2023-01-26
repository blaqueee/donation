package edu.jundev.donation.controller;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.dto.requests.DonationRequest;
import edu.jundev.donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDto> donate(@Valid @RequestBody DonationRequest form) {
        return ResponseEntity.ok(donationService.donate(form));
    }
}
