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
package org.eigenbase.sql;

import java.util.*;

import org.eigenbase.reltype.*;
import org.eigenbase.sql.validate.*;
import org.eigenbase.util.*;


/**
 * <code>SqlOperatorBinding</code> represents the binding of an {@link
 * SqlOperator} to actual operands, along with any additional information
 * required to validate those operands if needed.
 *
 * @author Wael Chatila
 * @version $Id$
 * @since Dec 16, 2004
 */
public abstract class SqlOperatorBinding
{
    //~ Instance fields --------------------------------------------------------

    protected final RelDataTypeFactory typeFactory;
    private final SqlOperator sqlOperator;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a SqlOperatorBinding.
     *
     * @param typeFactory Type factory
     * @param sqlOperator Operator which is subject of this call
     */
    protected SqlOperatorBinding(
        RelDataTypeFactory typeFactory,
        SqlOperator sqlOperator)
    {
        this.typeFactory = typeFactory;
        this.sqlOperator = sqlOperator;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @return bound operator
     */
    public SqlOperator getOperator()
    {
        return sqlOperator;
    }

    /**
     * @return factory for type creation
     */
    public RelDataTypeFactory getTypeFactory()
    {
        return typeFactory;
    }

    /**
     * Gets the string value of a string literal operand.
     *
     * @param ordinal zero-based ordinal of operand of interest
     *
     * @return string value
     */
    public String getStringLiteralOperand(int ordinal)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the integer value of a numeric literal operand.
     *
     * @param ordinal zero-based ordinal of operand of interest
     *
     * @return integer value
     */
    public int getIntLiteralOperand(int ordinal)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Determines whether a bound operand is NULL.
     *
     * <p>This is only relevant for SQL validation.
     *
     * @param ordinal zero-based ordinal of operand of interest
     * @param allowCast whether to regard CAST(constant) as a constant
     *
     * @return whether operand is null; false for everything except SQL
     * validation
     */
    public boolean isOperandNull(int ordinal, boolean allowCast)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @return the number of bound operands
     */
    public abstract int getOperandCount();

    /**
     * Gets the type of a bound operand.
     *
     * @param ordinal zero-based ordinal of operand of interest
     *
     * @return bound operand type
     */
    public abstract RelDataType getOperandType(int ordinal);

    /**
     * Collects the types of the bound operands into an array.
     *
     * @return collected array
     */
    public RelDataType [] collectOperandTypes()
    {
        RelDataType [] ret = new RelDataType[getOperandCount()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = getOperandType(i);
        }
        return ret;
    }

    /**
     * Returns the rowtype of the <code>ordinal</code>th operand, which is a
     * cursor.
     *
     * <p>This is only implemented for {@link SqlCallBinding}.
     *
     * @param ordinal Ordinal of the operand
     *
     * @return Rowtype of the query underlying the cursor
     */
    public RelDataType getCursorOperand(int ordinal)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves information about a column list parameter.
     *
     * @param ordinal ordinal position of the column list parameter
     * @param paramName name of the column list parameter
     * @param columnList returns a list of the column names that are referenced
     * in the column list parameter
     *
     * @return the name of the parent cursor referenced by the column list
     * parameter if it is a column list parameter; otherwise, null is returned
     */
    public String getColumnListParamInfo(
        int ordinal,
        String paramName,
        List<String> columnList)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Wraps a validation error with context appropriate to this operator call.
     *
     * @param e Validation error
     *
     * @return Error wrapped, if possible, with positional information
     *
     * @pre node != null
     * @post return != null
     */
    public abstract EigenbaseException newError(
        SqlValidatorException e);
}

// End SqlOperatorBinding.java
