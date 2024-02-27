package com.example.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Data
@Table(name = "sale_compaign")
@NoArgsConstructor
public class SaleCompaignData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private long startDate;
    private long endDate;

    public SaleCompaignData( String title,String startDate,String endDate) {
        this.id = id;
        this.title = title;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Instant startInstant = LocalDate.parse(startDate, dateFormatter).atStartOfDay().toInstant(java.time.ZoneOffset.UTC);
        Instant endInstant = LocalDate.parse(endDate, dateFormatter).atStartOfDay().toInstant(java.time.ZoneOffset.UTC);

        this.startDate = startInstant.toEpochMilli();
        this.endDate = endInstant.toEpochMilli();
    }
}
