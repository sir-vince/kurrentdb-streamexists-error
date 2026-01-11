package dk.sirvince.spike;

import io.kurrent.dbclient.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class AggregateStoreIntegrationTest {
    private KurrentDBClient kurrentDBClient;

    @BeforeEach
    public void before() {
        kurrentDBClient = KurrentDBClient.create(KurrentDBConnectionString.parseOrThrow("kurrentdb://localhost:2114?tls=false"));
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        UUID id = UUID.randomUUID();
        String streamId = "%s-%s".formatted("TestAggregate", id.toString());

        try {
            var writeResult = kurrentDBClient.appendToStream(
                    streamId,
                    AppendToStreamOptions.get().streamRevision(StreamState.noStream().toRawLong()),
                    EventData.builderAsJson("Test", "{\"value\":\"test\"}".getBytes()).build()
            ).thenCompose(writeResult1 -> kurrentDBClient.appendToStream(
                    streamId,
                    AppendToStreamOptions.get().streamRevision(StreamState.streamExists().toRawLong()),
                    //AppendToStreamOptions.get().streamRevision(writeResult1.getNextExpectedRevision().toRawLong()),
                    EventData.builderAsJson("Test", "{\"value\":\"test2\"}".getBytes()).build()
            )).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        var readResult = kurrentDBClient.readStream(streamId, ReadStreamOptions.get().fromStart());

        var events = readResult.get().getEvents();
    }
}


sealed interface TestEvents2  {
    record Created(UUID id, String value) implements TestEvents2 {}
    record Test1Event(UUID id, String value) implements TestEvents2 {}
    record Test2Event(UUID id, String value) implements TestEvents2 {}
}