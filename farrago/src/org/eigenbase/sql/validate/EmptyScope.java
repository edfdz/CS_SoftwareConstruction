/*
// $Id$
// Package org.eigenbase is a class library of data management components.
// Copyright (C) 2004-2005 The Eigenbase Project
// Copyright (C) 2004-2005 Disruptive Tech
// Copyright (C) 2005-2005 Red Square, Inc.
//
// This program is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by the Free
// Software Foundation; either version 2 of the License, or (at your option)
// any later version approved by The Eigenbase Project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package org.eigenbase.sql.validate;

import org.eigenbase.sql.*;
import org.eigenbase.resource.EigenbaseResource;

import java.util.List;

/**
 * Deviant implementation of {@link SqlValidatorScope} for the top of the scope
 * stack.
 *
 * <p>It is convenient, because we never need to check whether a scope's
 * parent is null. (This scope knows not to ask about its parents, just like
 * Adam.)
 *
 * @author jhyde
 * @version $Id$
 * @since Mar 25, 2003
 */
class EmptyScope implements SqlValidatorScope
{
    protected final SqlValidatorImpl validator;

    EmptyScope(SqlValidatorImpl validator)
    {
        this.validator = validator;
    }

    public SqlValidator getValidator()
    {
        return validator;
    }

    public SqlIdentifier fullyQualify(SqlIdentifier identifier)
    {
        return null;
    }

    public SqlNode getNode()
    {
        throw new UnsupportedOperationException();
    }

    public SqlValidatorNamespace resolve(String name,
        SqlValidatorScope[] ancestorOut,
        int[] offsetOut)
    {
        return null;
    }

    public void findAllColumnNames(String parentObjName, List result)
    {
    }

    public void findAllTableNames(List result)
    {
    }

    public String findQualifyingTableName(
        String columnName,
        SqlNode ctx)
    {
        throw validator.newValidationError(ctx,
            EigenbaseResource.instance().newColumnNotFound(columnName));
    }

    public void addChild(SqlValidatorNamespace ns, String alias)
    {
        // cannot add to the empty scope
        throw new UnsupportedOperationException();
    }

    public SqlWindow lookupWindow(String name)
    {
        // No windows defined in this scope.
        return null;
    }

    public boolean isMonotonic(SqlNode expr)
    {
        return expr instanceof SqlLiteral ||
            expr instanceof SqlDynamicParam ||
            expr instanceof SqlDataTypeSpec;
    }
}

// End EmptyScope.java
