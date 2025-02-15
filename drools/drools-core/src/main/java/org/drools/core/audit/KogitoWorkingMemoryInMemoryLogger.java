/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.core.audit;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.drools.core.WorkingMemory;
import org.drools.core.audit.event.LogEvent;
import org.kie.api.event.KieRuntimeEventManager;

import com.thoughtworks.xstream.XStream;

import static org.kie.utll.xml.XStreamUtils.createTrustingXStream;

/**
 * A logger of events generated by a working memory.
 * It stores its information in memory, so it can be retrieved later.
 */
public class KogitoWorkingMemoryInMemoryLogger extends KogitoWorkingMemoryLogger {

    private List<LogEvent> events = new ArrayList<>();

    public KogitoWorkingMemoryInMemoryLogger() {
    }

    public KogitoWorkingMemoryInMemoryLogger(final WorkingMemory workingMemory) {
        super(workingMemory);
    }

    public KogitoWorkingMemoryInMemoryLogger(final KieRuntimeEventManager session) {
        super(session);
    }

    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        events = (List<LogEvent>) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(events);
    }

    public String getEvents() {
        final XStream xstream = createTrustingXStream();
        StringWriter writer = new StringWriter();
        try (final ObjectOutputStream out = xstream.createObjectOutputStream(writer)) {
            out.writeObject(this.events);
        } catch (Throwable t) {
            throw new RuntimeException("Unable to create event output: " + t.getMessage());
        }
        return writer.toString();
    }

    /**
     * Clears all the events in the log.
     */
    public void clear() {
        this.events.clear();
    }

    public void logEventCreated(final LogEvent logEvent) {
        this.events.add(logEvent);
    }

    public List<LogEvent> getLogEvents() {
        return this.events;
    }

}
