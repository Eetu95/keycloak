/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.models.sessions.infinispan.initializer;

import java.io.Serializable;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class OfflinePersistentUserSessionLoaderContext implements SessionLoader.LoaderContext, Serializable {

    private final int sessionsTotal;
    private final int segmentsCount;
    private final int sessionsPerSegment;

    public OfflinePersistentUserSessionLoaderContext(int sessionsTotal, int sessionsPerSegment) {
        this.sessionsTotal = sessionsTotal;
        this.sessionsPerSegment = sessionsPerSegment;

        int segmentsCount = sessionsTotal / sessionsPerSegment;
        if (sessionsTotal % sessionsPerSegment >= 1) {
            segmentsCount = segmentsCount + 1;
        }
        this.segmentsCount = segmentsCount;
    }


    public int getSessionsTotal() {
        return sessionsTotal;
    }

    @Override
    public int getSegmentsCount() {
        return segmentsCount;
    }

    public int getSessionsPerSegment() {
        return sessionsPerSegment;
    }


    @Override
    public String toString() {
        return new StringBuilder("OfflinePersistentUserSessionLoaderContext [ ")
                .append(" sessionsTotal: ").append(sessionsTotal)
                .append(", sessionsPerSegment: ").append(sessionsPerSegment)
                .append(", segmentsCount: ").append(segmentsCount)
                .append(" ]")
                .toString();
    }
}
