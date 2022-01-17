package com.wotapi.extension.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wotapi.extension.model.Gamer;
import com.wotapi.extension.repository.ClanRepository;
import com.wotapi.extension.repository.ClanWarsEventRepository;
import com.wotapi.extension.repository.GamerRepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DataRefreshService {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("{wot-api.token}")
    private String application_id;
    private GamerRepository gamerRepository;
    private ClanRepository clanRepository;
    private ClanWarsEventRepository clanWarsEventRepository;

    @Autowired
    public DataRefreshService(GamerRepository gamerRepository, ClanRepository clanRepository,
                              ClanWarsEventRepository clanWarsEventRepository) {
        this.gamerRepository = gamerRepository;
        this.clanRepository = clanRepository;
        this.clanWarsEventRepository = clanWarsEventRepository;
    }

    @Scheduled(fixedRate = 60000, initialDelay = 1000)
    public void refreshGamerData() throws URISyntaxException {
        List<Gamer> allGamers = gamerRepository.findAll();
        for (AtomicInteger i = new AtomicInteger(0); i.get() < allGamers.size(); i.set(i.get() + 100)) {
            setTimeout(() -> {
                int lastIndexOfGamerInRequest =
                        (i.get() + 100) > allGamers.size() ? allGamers.size() - 1 : i.get() + 99;
                List<Gamer> gamersInRequest = allGamers.subList(i.get(), lastIndexOfGamerInRequest);
                StringBuilder stringBuilder = new StringBuilder();
                for (Gamer gamer:gamersInRequest) {
                    stringBuilder.append(gamer.getAccountId());
                    if (!(gamersInRequest.indexOf(gamer) == gamersInRequest.size() - 1)) stringBuilder.append(",");
                }
                URI uri = null;
                try {
                    uri = new URI("https://api.worldoftanks.com/wot/account/info/?application_id=" + application_id
                            + "&account_id=" + stringBuilder.toString() + "&fields=account_id,nickname");
                    JSONObject incomingGamerJSON = restTemplate.getForObject(uri, JSONObject.class);
                    incomingGamerJSON = (JSONObject) incomingGamerJSON.get("data");
                    for (Gamer gamer:gamersInRequest) {
                        try {
                            Gamer processedGamer = objectMapper.readValue(incomingGamerJSON
                                    .get(String.valueOf(gamer.getAccountId())).toString(), Gamer.class);
                            Gamer existingGamer = gamerRepository.findById(processedGamer.getAccountId()).get();
                            if (!processedGamer.equals(existingGamer)) gamerRepository.save(processedGamer);
                        } catch (Exception e) {
                            log.error("Couldn't process a record, account id: {}", gamer.getAccountId());
                        }
                    }
                } catch (URISyntaxException e) {
                    log.error("Couldn't create URI in refreshGamerData");
                }
            }, 101);
        }
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
