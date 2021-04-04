package org.example.tomasBarauskas.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.tomasBarauskas.model.CompanyAccount;
import org.example.tomasBarauskas.model.parking.ParkingZone;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingFine;
import org.example.tomasBarauskas.model.parking.parkingRecord.ParkingTicket;
import org.example.tomasBarauskas.model.user.User;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileJsonRW<E> {
    private final String PATH_USER_LIST = "target/users.json";
    private final String PATH_TICKET_LIST = "target/parkingTicket.json";
    private final String PATH_FINE_LIST = "target/parkingFine.json";
    private final String PATH_ZONE_LIST = "target/parkingZone.json";
    private final String PATH_COMPANY = "target/companyAccount.json";

    private ObjectMapper mapper = new ObjectMapper();

    public FileJsonRW() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public <E> void jsonWriteObjectListToFile(List<Object> objectList, String path) {
        try {
            mapper.writeValue(new File(path), objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> jsonReadUsersFromFile() {
        List<User> usersFromFile = new ArrayList<>();
        try {
            usersFromFile = mapper.readValue(new File(PATH_USER_LIST), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFromFile;
    }

    public List<ParkingTicket> jsonReadParkingTicketsFromFile() {
        List<ParkingTicket> ticketsFromFile = new ArrayList<>();
        try {
            ticketsFromFile = mapper.readValue(new File(PATH_TICKET_LIST), new TypeReference<List<ParkingTicket>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketsFromFile;
    }

    public List<ParkingFine> jsonReadParkingFineFromFile(){
        List<ParkingFine> fineFromFile = new ArrayList<>();
        try {
            fineFromFile = mapper.readValue(new File(PATH_FINE_LIST), new TypeReference<List<ParkingFine>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fineFromFile;
    }

    public List<ParkingZone> jsonReadParkingZoneFromFile(){
        List<ParkingZone> zonesFromFile = new ArrayList<>();
        try {
            zonesFromFile = mapper.readValue(new File(PATH_ZONE_LIST), new TypeReference<List<ParkingZone>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zonesFromFile;
    }

    public BigDecimal jsonReadCompanyAccount(){
        CompanyAccount companyAmount = null;
        try {
            companyAmount = mapper.readValue(new File(PATH_COMPANY), CompanyAccount.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companyAmount.getCompanyAccount();
    }
}