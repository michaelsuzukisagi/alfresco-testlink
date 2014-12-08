/*
 * Copyright (C) 2005-2014 Alfresco Software Limited.
 * This file is part of Alfresco
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.testlink;

import java.util.List;

import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;

public interface TestService
{
    /**
     * Gets the test project details.
     * @param name project name
     * @return {@link TestProject} project details
     */
    public abstract TestProject getTestProject(String name);

    /**
     * Get test case details from a test case name.
     * @param testcase String test case name
     * @param id String test case name
     * @return
     */
    public abstract TestCase getTestCase(String id);

    /**
     * Get test plan details from a test case name.
     * @param testcase String test case name
     * @param testplanId String test plan identifier
     * @param testplanId String test plan identifier
     * @return {@link TestPlan} test plan details
     */
    public abstract TestPlan getTestPlan(String testplanId, String projectId);

    /**
     * Get all the test cases in test plan, to run in automation. 
    
     * @param testPlanID test plan identifier
     * @return List of test ids from test plan. 
     */
    public abstract List<String> getTestCases(int testPlanID);

}