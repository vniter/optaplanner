/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.impl.score.stream.drools.uni;

import java.util.function.Function;

import org.optaplanner.core.api.score.stream.uni.UniConstraintCollector;
import org.optaplanner.core.impl.score.stream.drools.common.BiTuple;
import org.optaplanner.core.impl.score.stream.drools.common.DroolsAbstractGroupBy;
import org.optaplanner.core.impl.score.stream.drools.common.GroupByAccumulator;

final class DroolsUniToBiGroupBy<A, NewA, NewB>
        extends DroolsAbstractGroupBy<A, BiTuple<NewA, NewB>> {

    private final Function<A, NewA> groupKeyMapping;
    private final UniConstraintCollector<A, ?, NewB> collector;

    public DroolsUniToBiGroupBy(Function<A, NewA> groupKeyMapping, UniConstraintCollector<A, ?, NewB> collector) {
        this.groupKeyMapping = groupKeyMapping;
        this.collector = collector;
    }

    @Override
    protected GroupByAccumulator<A, BiTuple<NewA, NewB>> newAccumulator() {
        return new DroolsUniToBiGroupByAccumulator<>(groupKeyMapping, collector);
    }

}
