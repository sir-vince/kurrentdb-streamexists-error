# kurrentdb-streamexists-error
Demonstration of error occurring when using StreamExists

## Current behavior
I made a test upgrade from kurrentdb 25.0.0 to 25.1 and upgraded my client from com.eventstore:db-client-java:5.3.2 to io.kurrent:kurrentdb-client:1.1.0 after which the error occurs.

When using a specific revision id `AppendToStreamOptions.get().streamRevision(writeResult1.getNextExpectedRevision().toRawLong())` it works as expected and both events are persisted.

When using StreamState.streamExists() I get the following error / stacktrace:
`
AggregateStoreIntegrationTest > test() STANDARD_ERROR
    SLF4J(W): No SLF4J providers were found.
    SLF4J(W): Defaulting to no-operation (NOP) logger implementation
    SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
    java.util.concurrent.ExecutionException: io.grpc.StatusRuntimeException: UNKNOWN: Exception was thrown by handler.
        at java.base/java.util.concurrent.CompletableFuture.wrapInExecutionException(CompletableFuture.java:345)
        at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:440)
        at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:2094)
        at dk.sirvince.spike.AggregateStoreIntegrationTest.test(AggregateStoreIntegrationTest.java:33)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:218)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:214)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:139)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:69)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
        at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.processAllTestClasses(JUnitPlatformTestClassProcessor.java:135)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.access$000(JUnitPlatformTestClassProcessor.java:110)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor.stop(JUnitPlatformTestClassProcessor.java:104)
        at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.stop(SuiteTestClassProcessor.java:64)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
        at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
        at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
        at jdk.proxy1/jdk.proxy1.$Proxy4.stop(Unknown Source)
        at org.gradle.api.internal.tasks.testing.worker.TestWorker$3.run(TestWorker.java:200)
        at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
        at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
        at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
        at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
        at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:122)
        at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:72)
        at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
        at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
    Caused by: io.grpc.StatusRuntimeException: UNKNOWN: Exception was thrown by handler.
        at io.grpc.Status.asRuntimeException(Status.java:532)
        at io.grpc.stub.ClientCalls$StreamObserverToCallListenerAdapter.onClose(ClientCalls.java:564)
        at io.grpc.internal.ClientCallImpl.closeObserver(ClientCallImpl.java:565)
        at io.grpc.internal.ClientCallImpl.access$100(ClientCallImpl.java:72)
        at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1StreamClosed.runInternal(ClientCallImpl.java:733)
        at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1StreamClosed.runInContext(ClientCallImpl.java:714)
        at io.grpc.internal.ContextRunnable.run(ContextRunnable.java:37)
        at io.grpc.internal.SerializingExecutor.run(SerializingExecutor.java:133)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
        at java.base/java.lang.Thread.run(Thread.java:1474)
`

If i instead use a specific revision, no errors occur and the system acts as expected.

On the server logs (/var/log/kurrentdb/0.0.0.0-2113-cluster-node/log20260111.json) I see:
`
{"@t":"2026-01-11T15:32:37.3375492+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["14,850","1,221"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":14850,"diff":1221,"flushLength":"00:00:00.0017431","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":96}
{"@t":"2026-01-11T15:32:42.3353819+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["14,850"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":14850,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":104}
{"@t":"2026-01-11T15:33:12.3388166+00:00","@mt":"{filterName} is flushing at {checkpoint:N0}. Diff {diff:N0} ...","@r":["17,253","2,403"],"@l":"Verbose","@i":3497731389,"filterName":"streamExistenceFilter","checkpoint":17253,"diff":2403,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:12.3412016+00:00","@mt":"Flushed {pages:N0} pages out of {totalPages:N0}. {bytes:N0} bytes. Delay {delay} ms per batch. Total delay {totalDelay:N0} ms. Actively flushing: {activeFlushTime} {activeFlushRate:N2} MB/s. ","@r":["12","33,334","98,304","0","44.29"],"@l":"Verbose","@i":442284855,"pages":12,"totalPages":33334,"bytes":98304,"delay":128,"totalDelay":0,"activeFlushTime":"00:00:00.0022196","activeFlushRate":44.289062658630634,"SourceContext":"KurrentDB.Core.DataStructures.ProbabilisticFilter.FileStreamPersistence","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:12.3412562+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["17,253","2,403"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":17253,"diff":2403,"flushLength":"00:00:00.0024440","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:17.3419794+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["17,253"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":17253,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":104}
{"@t":"2026-01-11T15:33:47.3433298+00:00","@mt":"{filterName} is flushing at {checkpoint:N0}. Diff {diff:N0} ...","@r":["18,407","1,154"],"@l":"Verbose","@i":3497731389,"filterName":"streamExistenceFilter","checkpoint":18407,"diff":1154,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:47.3449173+00:00","@mt":"Flushed {pages:N0} pages out of {totalPages:N0}. {bytes:N0} bytes. Delay {delay} ms per batch. Total delay {totalDelay:N0} ms. Actively flushing: {activeFlushTime} {activeFlushRate:N2} MB/s. ","@r":["6","33,334","49,152","0","35.28"],"@l":"Verbose","@i":442284855,"pages":6,"totalPages":33334,"bytes":49152,"delay":128,"totalDelay":0,"activeFlushTime":"00:00:00.0013932","activeFlushRate":35.27993234176592,"SourceContext":"KurrentDB.Core.DataStructures.ProbabilisticFilter.FileStreamPersistence","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:47.3449768+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["18,407","1,154"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":18407,"diff":1154,"flushLength":"00:00:00.0016526","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:52.3447680+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["18,407"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":18407,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":111}
`

If i instead use a specific revision, no errors occur and the system acts as expected.

On the server logs (/var/log/kurrentdb/0.0.0.0-2113-cluster-node/log20260111.json) I see:
`
{"@t":"2026-01-11T15:32:37.3375492+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["14,850","1,221"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":14850,"diff":1221,"flushLength":"00:00:00.0017431","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":96}
{"@t":"2026-01-11T15:32:42.3353819+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["14,850"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":14850,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":104}
{"@t":"2026-01-11T15:33:12.3388166+00:00","@mt":"{filterName} is flushing at {checkpoint:N0}. Diff {diff:N0} ...","@r":["17,253","2,403"],"@l":"Verbose","@i":3497731389,"filterName":"streamExistenceFilter","checkpoint":17253,"diff":2403,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:12.3412016+00:00","@mt":"Flushed {pages:N0} pages out of {totalPages:N0}. {bytes:N0} bytes. Delay {delay} ms per batch. Total delay {totalDelay:N0} ms. Actively flushing: {activeFlushTime} {activeFlushRate:N2} MB/s. ","@r":["12","33,334","98,304","0","44.29"],"@l":"Verbose","@i":442284855,"pages":12,"totalPages":33334,"bytes":98304,"delay":128,"totalDelay":0,"activeFlushTime":"00:00:00.0022196","activeFlushRate":44.289062658630634,"SourceContext":"KurrentDB.Core.DataStructures.ProbabilisticFilter.FileStreamPersistence","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:12.3412562+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["17,253","2,403"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":17253,"diff":2403,"flushLength":"00:00:00.0024440","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":114}
{"@t":"2026-01-11T15:33:17.3419794+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["17,253"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":17253,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":104}
{"@t":"2026-01-11T15:33:47.3433298+00:00","@mt":"{filterName} is flushing at {checkpoint:N0}. Diff {diff:N0} ...","@r":["18,407","1,154"],"@l":"Verbose","@i":3497731389,"filterName":"streamExistenceFilter","checkpoint":18407,"diff":1154,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:47.3449173+00:00","@mt":"Flushed {pages:N0} pages out of {totalPages:N0}. {bytes:N0} bytes. Delay {delay} ms per batch. Total delay {totalDelay:N0} ms. Actively flushing: {activeFlushTime} {activeFlushRate:N2} MB/s. ","@r":["6","33,334","49,152","0","35.28"],"@l":"Verbose","@i":442284855,"pages":6,"totalPages":33334,"bytes":49152,"delay":128,"totalDelay":0,"activeFlushTime":"00:00:00.0013932","activeFlushRate":35.27993234176592,"SourceContext":"KurrentDB.Core.DataStructures.ProbabilisticFilter.FileStreamPersistence","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:47.3449768+00:00","@mt":"{filterName} has flushed at {checkpoint:N0}. Diff {diff:N0}. Took {flushLength}","@r":["18,407","1,154"],"@l":"Verbose","@i":2329891430,"filterName":"streamExistenceFilter","checkpoint":18407,"diff":1154,"flushLength":"00:00:00.0016526","SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":105}
{"@t":"2026-01-11T15:33:52.3447680+00:00","@mt":"{filterName} took checkpoint at position: {position:N0}.","@r":["18,407"],"@l":"Verbose","@i":618272726,"filterName":"streamExistenceFilter","position":18407,"SourceContext":"KurrentDB.Core.LogAbstraction.Common.StreamExistenceFilter","ProcessId":1,"ThreadId":111}
`


## Reproduction Steps
1. Checkout GIT repo listed in "Reproducible link"
2. Run `docker compose up -d`
3. Confirm KurrentDB is running with `docker ps`
4. Run `./gradlew test --tests dk.sirvince.spike.AggregateStoreIntegrationTest`
5. Exception is thrown
6. The first event "test" event is correctly persisted in KurrentDB, but the second "test2" event is not persisted

Notice, in the example I have moved to port 2114 due to port 2113 is in use on the local test system. 

## Expected behavior
Upon storing test events "test" and "test2" I expected both events to be persisted in order without an exception being thrown. 