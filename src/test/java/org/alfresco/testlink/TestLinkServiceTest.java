package org.alfresco.testlink;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestLinkServiceTest
{
    private String testLinkURL = "https://testlink.alfresco.com/lib/api/xmlrpc.php";
    private String devKey = "30d16ba28b8fa4748376f14ec4ce0d1e";
    private String testplan = "Ent5.0-AutomationFullRegression-v4";
    private String testcase = "AONE-14106";
    private String project = "AlfrescoOne";
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void connectNullUrl() throws TestLinkAPIException, MalformedURLException
    {
        new TestLinkService(null, devKey);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void connectNullKey() throws TestLinkAPIException, MalformedURLException
    {
        new TestLinkService(testLinkURL, null);
    }
    @Test
    public void connect() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        Assert.assertNotNull(service);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getTestProjectNull() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        service.getTestProject(null);
    }
    @Test
    public void getTestProject() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        TestProject testProject = service.getTestProject(project);
        Assert.assertNotNull(testProject);
    }
    @Test
    public void getTestCase() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        TestCase testCase = service.getTestCase(testcase);
        Assert.assertNotNull(testCase);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getTestCaseNull() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        service.getTestCase(null);
    }
    @Test
    public void getTestPlan() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        TestPlan testPlan = service.getTestPlan(testplan, project);
        Assert.assertNotNull(testPlan);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getTestPlanNull() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        service.getTestPlan(null, project);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getTestPlanNullProject() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        service.getTestPlan(testplan, null);
    }
    @Test
    public void getTestCases() throws TestLinkAPIException, MalformedURLException
    {
        TestLinkService service = new TestLinkService(testLinkURL, devKey);
        TestPlan testPlan = service.getTestPlan(testplan, project);
        List<String> testcases = service.getTestcases(testPlan.getId());
        Assert.assertNotNull(testcases);
    }
}
