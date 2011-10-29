/*
 * Copyright 2011 JBoss Inc
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

package org.drools.planner.examples.machinereassignment.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.api.domain.entity.PlanningEntity;
import org.drools.planner.api.domain.variable.PlanningVariable;
import org.drools.planner.api.domain.variable.ValueRangeFromSolutionProperty;
import org.drools.planner.examples.common.domain.AbstractPersistable;
import org.drools.planner.examples.pas.domain.AdmissionPart;
import org.drools.planner.examples.pas.domain.Bed;
import org.drools.planner.examples.pas.domain.solver.BedStrengthComparator;

@PlanningEntity() // TODO difficultyWeightFactoryClass = BedDesignationDifficultyWeightFactory.class)
@XStreamAlias("MrProcessAssignment")
public class MrProcessAssignment extends AbstractPersistable {

    private MrProcess process;
    private MrMachine originalMachine;

    private MrMachine machine;

    public MrProcess getProcess() {
        return process;
    }

    public void setProcess(MrProcess process) {
        this.process = process;
    }

    public MrMachine getOriginalMachine() {
        return originalMachine;
    }

    public void setOriginalMachine(MrMachine originalMachine) {
        this.originalMachine = originalMachine;
    }

    @PlanningVariable() // TODO strengthComparatorClass = BedStrengthComparator.class)
    @ValueRangeFromSolutionProperty(propertyName = "machineList")
    public MrMachine getMachine() {
        return machine;
    }

    public void setMachine(MrMachine machine) {
        this.machine = machine;
    }

    public MrProcessAssignment clone() {
        MrProcessAssignment clone = new MrProcessAssignment();
        clone.id = id;
        clone.process = process;
        clone.originalMachine = originalMachine;
        clone.machine = machine;
        return clone;
    }

    /**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used because the rule engine already
     * requires them (for performance in their original state).
     * @see #solutionHashCode()
     */
    public boolean solutionEquals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof MrProcessAssignment) {
            MrProcessAssignment other = (MrProcessAssignment) o;
            return new EqualsBuilder()
                    .append(process, other.process)
                    .append(machine, other.machine)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used because the rule engine already
     * requires them (for performance in their original state).
     * @see #solutionEquals(Object)
     */
    public int solutionHashCode() {
        return new HashCodeBuilder()
                .append(process)
                .append(machine)
                .toHashCode();
    }

    @Override
    public String toString() {
        return process + " @ " + machine;
    }

    public String getLabel() {
        return "Process " + getId();
    }

    public MrService getService() {
        return process.getService();
    }

    public MrLocation getLocation() {
        return machine == null ? null : machine.getLocation();
    }
    
}
