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
package org.kie.kogito.monitoring.core.common;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

public class MonitoringRegistry {

    private static CompositeMeterRegistry compositeMeterRegistry = Metrics.globalRegistry;

    private MonitoringRegistry() {
    }

    public static void addRegistry(MeterRegistry registry) {
        compositeMeterRegistry.add(registry);
    }

    public static CompositeMeterRegistry getDefaultMeterRegistry() {
        return compositeMeterRegistry;
    }
}
