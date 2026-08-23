package com.katok.molodcenteruserservice.usereventregistration;

import com.katok.molodcenteruserservice.event.EventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserEventScheduler {
    private final UserEventRegistrationService userEventRegistrationService;
    private final EventClient eventClient;
    private final TaskExecutor taskExecutor;

    @Value("${scheduler.user-event.limit}")
    private int limit;
    @Value("${scheduler.user-event.cooldown}")
    private int cooldown;

    @Async
    @Scheduled(cron = "${scheduler.user-event.cron}")
    public void executeDeleteUserEventRegistrationsTask() {
        Long lastEventId = null;

        while (true) {
            Pageable pageable = PageRequest.of(0, 500);
            Page<Long> eventIds = userEventRegistrationService.getDistinctEventIds(lastEventId, pageable);

            if (eventIds.isEmpty()) {
                break;
            }

            List<List<Long>> chunks = partitionList(eventIds.getContent(), 50);

            List<CompletableFuture<Void>> futures = chunks.stream()
                    .map(chunk -> CompletableFuture.runAsync(() ->
                        executeDeleteUserEventRegistration(chunk), taskExecutor
                    )).toList();


            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            lastEventId = eventIds.getContent().getLast();

            if (eventIds.isLast()) {
                break;
            }
        }
    }

    public void executeDeleteUserEventRegistration(List<Long> eventIds) {
        for (Long eventId : eventIds) {
            if (eventClient.getEventById(eventId).hasBody()) {
                continue;
            }

            int deletedParameters;

            do {
                deletedParameters = userEventRegistrationService.deleteEventRegistrations(null, eventId, 5000);
            } while (deletedParameters >= 5000);
        }
    }

    private <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }
}
