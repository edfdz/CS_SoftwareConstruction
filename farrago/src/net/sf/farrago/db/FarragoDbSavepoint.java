/*
// Licensed to DynamoBI Corporation (DynamoBI) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  DynamoBI licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at

//   http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
*/
package net.sf.farrago.db;

import net.sf.farrago.fem.fennel.*;
import net.sf.farrago.fennel.*;
import net.sf.farrago.session.*;


/**
 * FarragoDbSavepoint implements the {@link
 * net.sf.farrago.session.FarragoSessionSavepoint} interface in terms of a
 * {@link FarragoDbSession}.
 *
 * @author John V. Sichi
 * @version $Id$
 */
class FarragoDbSavepoint
    implements FarragoSessionSavepoint
{
    //~ Instance fields --------------------------------------------------------

    /**
     * Session which created this savepoint.
     */
    FarragoDbSession session;

    /**
     * ID generated by session (unique within session scope).
     */
    private int id;

    /**
     * Name assigned by user, or null if unnamed.
     */
    private String name;

    /**
     * Handle to underlying Fennel savepoint.
     */
    private FennelSvptHandle fennelSvptHandle;

    //~ Constructors -----------------------------------------------------------

    FarragoDbSavepoint(
        int id,
        String name,
        FennelSvptHandle fennelSvptHandle,
        FarragoDbSession session)
    {
        this.id = id;
        this.name = name;
        this.fennelSvptHandle = fennelSvptHandle;
        this.session = session;
    }

    //~ Methods ----------------------------------------------------------------

    // implement FarragoSessionSavepoint
    public int getId()
    {
        return id;
    }

    // implement FarragoSessionSavepoint
    public String getName()
    {
        return name;
    }

    // implement Object
    public boolean equals(Object obj)
    {
        if (!(obj instanceof FarragoDbSavepoint)) {
            return false;
        }
        FarragoDbSavepoint other = (FarragoDbSavepoint) obj;
        return (id == other.id) && (session == other.session);
    }

    // implement Object
    public int hashCode()
    {
        return id;
    }

    FennelSvptHandle getFennelSvptHandle()
    {
        return fennelSvptHandle;
    }
}

// End FarragoDbSavepoint.java
