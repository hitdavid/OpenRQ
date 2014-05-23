/*
 * Copyright 2014 Jose Lopes
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.fec.openrq.parameters;


import static net.fec.openrq.util.arithmetic.ExtraMath.ceilDiv;
import net.fec.openrq.util.arithmetic.ExtraMath;
import net.fec.openrq.util.rq.SystematicIndices;


/**
 */
final class InternalFunctions {

    // requires individually bounded arguments
    static long getPossibleTotalSymbols(long F, int T) {

        return ceilDiv(F, T);
    }

    // requires individually and in unison bounded arguments
    static int getTotalSymbols(long F, int T) {

        return (int)ceilDiv(F, T); // downcast never overflows since F and T are bounded
    }

    // requires valid arguments
    static int KL(int WS, int T, int Al, int n) {

        final int upper_bound = WS / subSymbolSize(T, Al, n);
        return SystematicIndices.floor(upper_bound);
    }

    // requires valid arguments
    static int minWS(int KL, int T, int Al, int n) {

        return KL * subSymbolSize(T, Al, n);
    }

    // since interleaving is disabled, this should always return T
    private static int subSymbolSize(int T, int Al, int n) {

        return Al * ExtraMath.ceilDiv(T, Al * n);
    }

    private InternalFunctions() {

        // not instantiable
    }
}
