package com.wotapi.extension.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wotapi.extension.model.Clan;
import com.wotapi.extension.model.Gamer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DataRefreshService {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${wot-api.token}")
    private String application_id;
    private GamerService gamerService;
    private ClanService clanService;
    private ClanWarsEventService clanWarsEventService;

    @Autowired
    public DataRefreshService(GamerService gamerService, ClanService clanService,
                              ClanWarsEventService clanWarsEventService) {
        this.gamerService = gamerService;
        this.clanService = clanService;
        this.clanWarsEventService = clanWarsEventService;
    }

    @Transactional
    @Scheduled(fixedRate = 60000, initialDelay = 1000)
    public void refreshGamerData() throws InterruptedException {
        List<Gamer> allGamers = gamerService.findAllGamers();
        for (AtomicInteger i = new AtomicInteger(0); i.get() < allGamers.size(); i.set(i.get() + 100)) {
            Thread.sleep(101);
                int lastIndexOfGamerInRequest =
                        (i.get() + 100) > allGamers.size() ? allGamers.size() - 1 : i.get() + 99;
                List<Gamer> gamersInRequest = allGamers.subList(i.get(), lastIndexOfGamerInRequest + 1);
                StringBuilder stringBuilder = new StringBuilder();
                for (Gamer gamer:gamersInRequest) {
                    stringBuilder.append(gamer.getAccountId());
                    if (!(gamersInRequest.indexOf(gamer) == gamersInRequest.size() - 1)) stringBuilder.append(",");
                }
                URI uri = null;
                try {
                    uri = new URI("https://api.worldoftanks.com/wot/account/info/?application_id=" + application_id
                            + "&account_id=" + stringBuilder.toString() + "&fields=account_id,nickname,clan_id");
                    LinkedHashMap incomingGamerJSON = restTemplate.getForObject(uri, LinkedHashMap.class);
                    incomingGamerJSON = (LinkedHashMap) incomingGamerJSON.get("data");
                    for (Gamer gamer:gamersInRequest) {
                        try {
                            LinkedHashMap individualGamerData = (LinkedHashMap) incomingGamerJSON
                                    .get(String.valueOf(gamer.getAccountId()));
                            Gamer processedGamer = new Gamer((Integer) individualGamerData.get("account_id"),
                                    individualGamerData.get("nickname").toString(), null, null);
                            processedGamer.setFamePoints(getFamePoints(gamer));
                            Optional<Clan> gamerClan = clanService
                                    .findClanById((Integer) individualGamerData.get("clan_id"));
                            if (gamerClan.isPresent()) processedGamer.setClan(gamerClan.get());
                            else {
                                Clan newClan = new Clan();
                                newClan.setClanId((Integer) individualGamerData.get("clan_id"));
                                processedGamer.setClan(clanService.insertClan(newClan));
                            }
                            Gamer existingGamer = gamerService.findGamerById(processedGamer.getAccountId())
                                    .orElse(null);
                            if (!Objects.isNull(existingGamer) && !processedGamer.equals(existingGamer)) {
                                gamerService.insertGamer(processedGamer);
                            }
                        } catch (Exception e) {
                            log.error("Couldn't process a record, account id: {}, error: {}", gamer.getAccountId(), e);
                        }
                    }
                } catch (URISyntaxException e) {
                    log.error("Couldn't create URI in refreshGamerData");
                }
        }
    }


    //TODO implement once fame is available on player profiles
    private Integer getFamePoints(Gamer gamer) throws InterruptedException {
        Thread.sleep(99);
        return null;
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
