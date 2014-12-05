/*
 * Copyright (C) 2005-2012 Alfresco Software Limited.
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
/**
 * Service that interacts with Alfresco test link.
 * Test Link is the repository for alfresco regression test data.
 * This class provide the list of tests that needs to be executed
 * and is also able to update records in test link using the TestLink
 * API.
 * @author Michael Suzuki
 *
 */
public class TestLinkService
{
 
    private final TestLinkAPI testlinkAPIClient;
    /**
     * Constructor.
     * @param testLinkURL URL of testlink service
     * @param devKey API devKey
     * @throws TestLinkAPIException if error
     * @throws MalformedURLException if incorrect url
     */
    public TestLinkService(final String testLinkURL, final String devKey) throws TestLinkAPIException, MalformedURLException
    {
        if(StringUtils.isEmpty(testLinkURL))
        {
            throw new IllegalArgumentException("TestLink url is required");
        }
        if(StringUtils.isEmpty(devKey))
        {
            throw new IllegalArgumentException("TestLink devKey is required");
        }
        testlinkAPIClient = new TestLinkAPI(new URL(testLinkURL), devKey);
    }
    /**
     * Gets the test project details.
     * @param name project name
     * @return {@link TestProject} project details
     */
    public TestProject getTestProject(final String name)
    {
        if(StringUtils.isEmpty(name))
        {
            throw new IllegalArgumentException("TestLink project name is required");
        }
        return testlinkAPIClient.getTestProjectByName(name);
    }
    /**
     * Get test case details from a test case name.
     * @param testcase String test case name
     * @param id String test case name
     * @return
     */
    public TestCase getTestCase(final String id)
    {
        if(StringUtils.isEmpty(id))
        {
            throw new IllegalArgumentException("TestLink testcase name is required");
        }
        return testlinkAPIClient.getTestCaseByExternalId(id, 1);
    }
    /**
     * Get test plan details from a test case name.
     * @param testcase String test case name
     * @param testplanId String test plan identifier
     * @param testplanId String test plan identifier
     * @return {@link TestPlan} test plan details
     */
    public TestPlan getTestPlan(final String testplanId,final String projectId)
    {
        if(StringUtils.isEmpty(testplanId))
        {
            throw new IllegalArgumentException("TestLink test plan name is required");
        }
        if(StringUtils.isEmpty(projectId))
        {
            throw new IllegalArgumentException("TestLink project plan name is required");
        }
        return testlinkAPIClient.getTestPlanByName(testplanId, projectId);
    }
    /**
     * Get all the test case externalIds in the test plan to run in automation. 
     * The externalIds are the test case identifier used by alfresco.
     * @param testPlanID test plan identifier
     * @return List of test case names from test plan. 
     */
    public List<String> getTestCases(final int testPlanID) 
    { 
        TestCase[] testcases = 
                testlinkAPIClient.getTestCasesForTestPlan(testPlanID, null, null, null, null, null, null, null, ExecutionType.AUTOMATED, true, TestCaseDetails.FULL);
        if(testcases != null && testcases.length > 0)
        {
            List<String> testcaseNames = new ArrayList<String>(); 
            for (TestCase testCase : testcases) 
            { 
                TestCase finalTestcase = testlinkAPIClient.getTestCase( testCase.getId(), null, null);
                testcaseNames.add(finalTestcase.getFullExternalId()); 
            }
            return testcaseNames; 
        }
        return Collections.<String>emptyList();
    } 
    
}
