package org.drools.lang.descr;

/*
 * Copyright 2005 JBoss Inc
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

import java.util.ArrayList;
import java.util.List;

public class NotDescr extends BaseDescr
    implements
    ConditionalElementDescr {

    /**
     * 
     */
    private static final long serialVersionUID = 4650543951506472407L;
    private final List descrs = new ArrayList( 1 );

    public NotDescr() {
    }

    public NotDescr(final BaseDescr descr) {
        addDescr( descr );
    }

    public void addDescr(final BaseDescr baseDescr) {
        this.descrs.add( baseDescr );
    }

    public List getDescrs() {
        return this.descrs;
    }

}