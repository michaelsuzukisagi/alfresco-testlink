/*
 * Copyright (C) 2005-2014 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.utils;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate HttpUtil methods.
 * 
 * @author Michael Suzuki
 * @since 1.0
 */
public class HttpUtilTest
{
    @Test
    public void instantiate()
    {
        Assert.assertNotNull(new HttpUtil());
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setMessageBodyNull() throws UnsupportedEncodingException
    {
        JSONObject json = null;
        HttpUtil.setMessageBody(json);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setMessageBodyNullString() throws UnsupportedEncodingException
    {
        String json = null;
        HttpUtil.setMessageBody(json);
    }
    
    @Test
    public void setMessageBodyString() throws UnsupportedEncodingException, JSONException
    {
        String json = "{name:michael}";
        StringEntity value = HttpUtil.setMessageBody(json);
        Assert.assertNotNull(value);
        JSONObject body = new JSONObject();
        body.put("name", "michael");
        StringEntity val = HttpUtil.setMessageBody(body);
        Assert.assertNotNull(val);
    }
}